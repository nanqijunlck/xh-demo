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

    @ApiModelProperty("故障编码")
    private String questionCode;

    @ApiModelProperty("故障描述")
    private String questionContent;

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
