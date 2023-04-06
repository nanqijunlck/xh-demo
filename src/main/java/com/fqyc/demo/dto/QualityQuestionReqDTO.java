package com.fqyc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lck
 * @Date 2023/3/16 18:45
 * @Version 1.0
 * @Desc
 */
@Data
public class QualityQuestionReqDTO implements Serializable {

    @ApiModelProperty("检测工位")
    private String roleCode;

    @ApiModelProperty("商户编码")
    private String merchantCode;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("编码")
    private String benChangCode;

    @ApiModelProperty("状态")
    private String qualityStatus;

    @ApiModelProperty("故障描述")
    private String qualityContent;

    @ApiModelProperty("计划单号")
    private String orderCode;
}
