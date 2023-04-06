package com.fqyc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lck
 * @Date 2023/3/16 18:45
 * @Version 1.0
 * @Desc
 */
@Data
@Builder
public class QuestionStatisticsRspDTO implements Serializable {

    @ApiModelProperty("故障编码")
    private String questionCode;

    @ApiModelProperty("故障描述")
    private String questionContent;

    @ApiModelProperty("故障数量")
    private Integer questionCount;

    @ApiModelProperty("百分比")
    private String questionPercent;
}
