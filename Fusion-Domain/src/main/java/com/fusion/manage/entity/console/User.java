package com.fusion.manage.entity.console;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fusion.common.framework.domain.TrackableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by zhou.xu on 2019/3/5.
 */

@Getter
@Setter
@ApiModel(value = "User",description = "用户")
public class User extends TrackableEntity{

    @ApiModelProperty(value = "用户所属部门id")
    private String departmentId;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String pwd;

    @ApiModelProperty(value = "用户生日")
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date birthday;

    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    @ApiModelProperty(value = "用户电话")
    private String mobilePhone;

    @ApiModelProperty(value = "用户部门")
    private String unit;

    @ApiModelProperty(value = "起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty(value = "菜单权限列表")
    private List<Menu> menuList;

    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    public String getCredentialsSalt() {
        return username + salt + salt;
    }

    public static User newInstance() { return new User(); }
}

