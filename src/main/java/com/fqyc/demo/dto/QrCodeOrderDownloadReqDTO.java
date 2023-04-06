package com.fqyc.demo.dto;

import com.fqyc.demo.dto.base.PageBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实体表
 *
 * @author lck
 * @date 2020/06/17  17:18
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeOrderDownloadReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("二维码")
    private String qrcode;

    @ApiModelProperty("计划单号")
    private String orderCode;

    @ApiModelProperty("编码")
    private String benChangCode;

    @ApiModelProperty("客户代码")
    private String merchantCode;

    @ApiModelProperty("客户型号")
    private String merchantSpe;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;
}
