package com.fusion.manage.restful.console;

import com.alibaba.fastjson.JSON;
import com.fusion.common.framework.Constants;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.common.framework.rest.Node;
import com.fusion.common.framework.rest.impl.AbstractRestServerImpl;
import com.fusion.manage.consumer.ConsoleConsumer;
import com.fusion.manage.entity.console.Menu;
import com.fusion.manage.provider.console.MenuProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by yaju.jiao on 2019/3/4.
 */
@Api(value = "MenuRestServer", description = "菜单-接口层")
@RequestMapping(Constants.RestPathPrefix.MODEL_CONSOLE + "menu")
@RestController
public class MenuRestServer extends AbstractRestServerImpl<Menu> {

    private static Logger log = LogManager.getLogger(MenuRestServer.class);

    @Override
    public MenuProvider getBaseDubboInterface() {
        return ConsoleConsumer.getMenuProvider();
    }

    @ApiOperation(value = "新建菜单时获取可能的上级菜单列表", notes = "", response = JsonViewObject.class)
    @RequestMapping(value = "getLowerMenu", method = RequestMethod.GET)
    public JsonViewObject getLowerMenu(@ApiParam(value = "一级菜单id", required = true) @QueryParam("id") String id) {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            List<Menu> menuList = getBaseDubboInterface().getLowerMenu(id);
            jsonViewObject.success(menuList);
        } catch (DubboProviderException e) {
            jsonViewObject.fail("获取上级目录失败, " + e.getMessage());
            log.error("MenuRestServer getLowerMenu error, id=" + id);
        }
        return jsonViewObject;
    }

    @ApiOperation(value = "获取树形菜单列表", notes = "", response = JsonViewObject.class)
    @RequestMapping(value = "getTreeMenu", method = RequestMethod.GET)
    public JsonViewObject getTreeMenu(){
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            List<Node> treeMenuList = getBaseDubboInterface().getTreeMenu();
            jsonViewObject.success(treeMenuList);
        } catch (DubboProviderException e) {
            jsonViewObject.fail("获取树形菜单列表失败, " + e.getMessage());
            log.error("MenuRestServer getTreeMenu error");
        }
        return jsonViewObject;
    }

    @ApiOperation(value = "批量更新", notes = "批量更新", response = JsonViewObject.class,
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @RequestMapping(value = "updateBatch", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public JsonViewObject updateBatch(@ApiParam(value = "菜单", required = true) @RequestBody String jsonStr) {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        try {
            List<Map<String, Object>> menuList = (List<Map<String, Object>>) JSON.parse(jsonStr);
            if (menuList.isEmpty()) {
                return jsonViewObject.fail("批量更改列表为空");
            }
            getBaseDubboInterface().updateBatch(menuList);
            jsonViewObject.success();
        } catch (DubboProviderException e) {
            jsonViewObject.fail("更改菜单列表失败, " + e.getMessage());
            log.error("MenuRestServer updateBatch error", e.getMessage());
        }
        return jsonViewObject;
    }

}
