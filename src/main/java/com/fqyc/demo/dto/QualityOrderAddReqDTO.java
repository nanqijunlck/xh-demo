package com.fqyc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
@ApiOperation(value = "质检记录", notes = "质检记录")
public class QualityOrderAddReqDTO extends SaleOrderBaseDTO {

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("质检状态")
    private String qualityStatus;

    @ApiModelProperty("故障编码")
    private String questionCode;

    @ApiModelProperty("故障描述")
    private String questionContent;

    @ApiModelProperty("维修内容")
    private String repairQuestionContent;
}
