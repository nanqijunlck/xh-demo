package com.fqyc.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fqyc.demo.dto.base.PageBase;
import com.fqyc.demo.dto.base.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lck
 * @Date 2023/3/15 16:13
 * @Version 1.0
 * @Desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation(value = "质检记录", notes = "质检记录")
public class QualityOrderRequestDTO extends PageBase implements Serializable {

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty(value = "计划单号")
    private String orderCode;

    @ApiModelProperty("编码")
    private String benChangCode;

    @ApiModelProperty("客户代码")
    private String merchantCode;

    @ApiModelProperty("客户型号")
    private String merchantSpe;

    @ApiModelProperty("质检状态")
    private String qualityStatus;

    @ApiModelProperty("故障编码")
    private String questionCode;
}
