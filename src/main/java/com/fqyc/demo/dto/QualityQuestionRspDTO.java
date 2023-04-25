package com.fqyc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lck
 * @Date 2023/3/16 18:45
 * @Version 1.0
 * @Desc
 */
@Data
@Builder
public class QualityQuestionRspDTO implements Serializable {

    @ApiModelProperty("计划单号")
    private String orderCode;

    @ApiModelProperty("客户代码")
    private String merchantCode;

    @ApiModelProperty("编码")
    private String benChangCode;

    @ApiModelProperty("客户型号")
    private String merchantSpe;

    @ApiModelProperty("数量")
    private Integer quantity;


    @ApiModelProperty("扫码记录数")
    private Integer scanRecordCount;

    @ApiModelProperty("扫码记录成功数量")
    private Integer scanRecordSuccessCount;

    @ApiModelProperty("扫码记录异常数量")
    private Integer scanRecordErrorCount;

    @ApiModelProperty("一次性合格率")
    private String noQuestionPercent;

    @ApiModelProperty("百分百下线合格率")
    private String lastSuccessPercent;
}
