package com.fusion.common.framework.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 继承自该类的entity表示是可被跟踪的，可以查询对应实体
 *
 * @author weijun.yu
 */
@Getter
@Setter
public class TrackableEntity implements Serializable {

    private static final long serialVersionUID = -4052705808523280313L;

    private String id;

    @ApiModelProperty(value = "记录创建时间", required = true, example = "2016-08-01")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

//    @ApiModelProperty(value = "记录更新时间", required = true, example = "2016-08-01 12:24:36")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date updateDate = new Date();

    @ApiModelProperty(value = "扩展字段，例如：{\"key1\":\"value1\",\"key2\":\"value2\"}")
    private Map<String, Object> expandMap;

    protected TrackableEntity() {
    }

}
