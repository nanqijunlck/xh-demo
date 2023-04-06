package com.fqyc.demo.dto;

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
public class ScanQueryRequestDTO extends PageDTO implements Serializable {

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("商户code")
    private String merchantCode;

    @ApiModelProperty("商户name")
    private String merchantName;

    @ApiModelProperty("员工编码")
    private String benChangSpe;

    @ApiModelProperty("员工姓名")
    private String practicalEle;

    @ApiModelProperty("头像")
    private String inputVoltage;

    @ApiModelProperty("创建时间")
    private Integer number;

    @ApiModelProperty("创建时间")
    private String digitShow;

    @ApiModelProperty("头像")
    private String machineType;

    @ApiModelProperty("创建时间")
    private String qualityStatus;
    @ApiModelProperty("头像")
    private String qualityContent;

    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("头像")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    private String createUser;
    @ApiModelProperty("头像")
    private String updateUser;
}
