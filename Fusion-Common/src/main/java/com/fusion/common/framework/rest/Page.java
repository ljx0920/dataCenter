package com.fusion.common.framework.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * {'total':64,'pageNum':1,'maxSize':10,'pageSize':10,'condition':''};
 *
 * @author daowan.hu
 */
@Getter
@Setter
@ApiModel
public class Page<T> implements Serializable {
    /**
     * 每页数据条数
     */
    @ApiModelProperty(value = "每页显示的总条数", example = "10", dataType = "int")
    private int pageSize = 10;
    /**
     * 总的数据数
     */
    @ApiModelProperty(value = "总的数据数", example = "10", dataType = "int")
    private int total = 0;
    /**
     * 起始页码
     */
    @ApiModelProperty(value = "当前页", example = "1", dataType = "int")
    private int pageNum = 1;
    /**
     * 起始行数
     */
    private int startRowNum = 0;

    private Object condition;

    private List<T> rows;

    public Page() {
    }

    public Page(int pageSize, int total, int pageNum) {
        this.pageSize = pageSize;
        this.total = total;
        this.pageNum = pageNum;
        this.startRowNum = pageSize * (pageNum - 1);
    }

    public int getTotalPageNum() {
        return (total + pageSize - 1) / pageSize;
    }

    public static Page newInstance() { return new Page(); }

    public static <T> Page<T> newInstance(int pageSize, int total, int pageNum) {
        return new Page<>(pageSize, total, pageNum);
    }
}
