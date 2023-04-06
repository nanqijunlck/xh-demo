package com.fqyc.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fqyc.demo.dto.base.PageBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lck
 * @Date 2023/3/18 14:57
 * @Version 1.0
 * @Desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderPageQueryDTO extends PageBase {

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

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;
}
