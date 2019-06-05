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
@ApiModel(value = "SystemParam" ,description = "系统参数")
public class SystemParam extends TrackableEntity {

    @ApiModelProperty(value = "系统名")
    private String name;

    @ApiModelProperty(value = "系统参数类别id")
    private String systemParamCatalogId;

    @ApiModelProperty(value = "系统键")
    private String systemKey;

    @ApiModelProperty(value = "系统值")
    private String systemValue;

    public static SystemParam newInstance() { return new SystemParam(); }
}
