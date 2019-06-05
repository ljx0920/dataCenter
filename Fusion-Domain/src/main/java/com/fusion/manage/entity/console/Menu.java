package com.fusion.manage.entity.console;

import com.fusion.common.framework.domain.TrackableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yaju.jiao on 2019/3/4.
 */
@Getter
@Setter
@ApiModel(value = "Menu", description = "菜单")
public class Menu extends TrackableEntity {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级菜单id")
    private String parentId;

    @ApiModelProperty(value = "上级菜单名称")
    private String parentName;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "菜单级别")
    private Integer level;

    public static Menu newInstance() {
        return new Menu();
    }
}
