package com.fqyc.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fqyc.demo.dto.base.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @Author lck
 * @Date 2023/3/15 16:13
 * @Version 1.0
 * @Desc
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "计划单基本五要素", notes = "计划单基本五要素")
public class SaleOrderBaseDTO implements Serializable {

    @ApiModelProperty(value = "计划单号")
    @ExcelProperty(value = "计划单号")
    private String orderCode;

    @ApiModelProperty("编码")
    @ExcelProperty(value = "编码")
    private String benChangCode;

    @ApiModelProperty("客户代码")
    @ExcelProperty(value = "客户代码")
    private String merchantCode;

    @ApiModelProperty("客户型号")
    @ExcelProperty(value = "客户型号")
    private String merchantSpe;

    @ApiModelProperty("数量")
    @ExcelProperty(value = "数量")
    private Integer quantity;
}
