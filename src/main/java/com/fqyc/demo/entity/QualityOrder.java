package com.fqyc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体表
 *
 * @author lck
 * @date 2020/06/17  17:18
 * @since 1.0
 */
@Data
@TableName("t_quality_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualityOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("计划单号")
    private String orderCode;

    @ApiModelProperty("检测工位")
    private String roleCode;

    @ApiModelProperty("客户代码")
    private String merchantCode;

    @ApiModelProperty("客户型号")
    private String merchantSpe;

    @ApiModelProperty("编码")
    private String benChangCode;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("质检状态")
    private String qualityStatus;

    @ApiModelProperty("故障编码")
    private String questionCode;

    @ApiModelProperty("故障描述")
    private String questionContent;

    @ApiModelProperty("维修内容")
    private String repairContent;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
