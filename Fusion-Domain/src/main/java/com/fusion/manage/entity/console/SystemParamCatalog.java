package com.fusion.manage.entity.console;

import com.fusion.common.framework.domain.TrackableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhou.xu on 2019/3/6.
 */
@Setter
@Getter
@ApiModel(value = "SystemParamCatalog" , description = "系统参数类别")
public class SystemParamCatalog extends TrackableEntity{

    @ApiModelProperty(value = "上级目录id")
    private String parentId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "系统参数类别名")
    private  String name;

    public static SystemParamCatalog newInstance(){return new SystemParamCatalog();}
}
