package com.fusion.manage.restful.console;

import com.fusion.common.framework.Constants;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.common.framework.rest.impl.AbstractRestServerImpl;
import com.fusion.manage.config.PasswordHelper;
import com.fusion.manage.consumer.ConsoleConsumer;
import com.fusion.manage.entity.console.User;
import com.fusion.manage.provider.console.UserProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by zhou.xu on 2019/3/5.
 */
@Api(value = "UserRestServer" ,description = "用户-接口层")
@RequestMapping(Constants.RestPathPrefix.MODEL_CONSOLE + "user")
@RestController
public class UserRestServer extends AbstractRestServerImpl<User>{
    private static Logger log = LogManager.getLogger(UserRestServer.class);

    @Override
    public UserProvider getBaseDubboInterface() {
        return ConsoleConsumer.getUserProvider();
    }

    @Autowired
    private PasswordHelper passwordHelper;

    @ApiOperation(value = "注册用户信息", notes = "注册用户信息", response = JsonViewObject.class,
        produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    JsonViewObject register(@ApiParam(value = "新增用户信息", required = true) @RequestBody User entity) {
        passwordHelper.encryptPassword(entity);
        return super.save(entity);
    }

    @ApiOperation(value = "登录", notes = "登录", response = JsonViewObject.class,
        produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    JsonViewObject login(@ApiParam(value = "用户名和密码", required = true) @RequestBody Map<String, String> map) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        String username = map.get("username");
        String pwd = map.get("pwd");
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            log.error("UserRestServer login fail, " + ice);
            return jsonViewObject.fail("密码错误");
        } catch (UnknownAccountException uae) {
            log.error("UserRestServer login fail, " + uae);
            return jsonViewObject.fail("用户名不存在");
        } catch (DisabledAccountException dae) {
            log.error("UserRestServer login fail, " + dae);
            return jsonViewObject.fail("此用户已删除");
        } catch (ExpiredCredentialsException ece) {
            log.error("UserRestServer login fail, " + ece);
            return jsonViewObject.fail("用户过期");
        }
        User user = getBaseDubboInterface().findByUsername(username);
        subject.getSession().setAttribute("user", user);
        return jsonViewObject.success(user, "登录成功");
   }

    @ApiOperation(value = "自动登录", notes = "自动登录", response = JsonViewObject.class)
    @RequestMapping(value = "autoLogin", method = RequestMethod.GET)
    JsonViewObject autoLogin() throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        if (user == null) {
            return jsonViewObject.fail("请重新登录");
        }
        subject.getSession().setAttribute("user", user);
        return jsonViewObject.success(user, "登录成功");
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", response = JsonViewObject.class)
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    JsonViewObject logout() {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().setAttribute("user", null);
        return jsonViewObject.success("退出登录成功");
    }
}
