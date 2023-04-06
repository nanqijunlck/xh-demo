package com.fqyc.demo.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lck
 * @Date 2022/12/6 21:24
 * @Version 1.0
 * @Desc
 */

@Data
public class PageDTO<T> extends PageBase {
    @ApiModelProperty("总条数")
    private int totalCount;
    @ApiModelProperty("总页数")
    private int totalPage;
    @ApiModelProperty("数据")
    private java.util.List<T> data;

    public PageDTO(int currentPage, int pageSize) {
    }

    public PageDTO() {
    }
}
