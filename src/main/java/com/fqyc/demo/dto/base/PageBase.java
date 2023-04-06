package com.fqyc.demo.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author lck
 * @Date 2022/12/6 22:02
 * @Version 1.0
 * @Desc
 */
@Data
public class PageBase {
    @ApiModelProperty("当前页，从第1页开始，不传默认为1")
    private int currentPage = 1;
    @ApiModelProperty("每页显示条数，不传默认20")
    private int pageSize = 20;

    private int getOffset() {
        return (currentPage - 1) * pageSize;
    }

    public PageBase() {
    }
}
