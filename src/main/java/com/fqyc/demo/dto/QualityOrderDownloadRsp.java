package com.fqyc.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 实体表
 *
 * @author lck
 * @date 2020/06/17  17:18
 * @since 1.0
 */
@Data
@Api("Excel导出")
public class QualityOrderDownloadRsp {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "二维码", index = 0)
    @ApiModelProperty("二维码")
    private String qrCode;

    @ExcelProperty(value = "计划单号", index = 1)
    @ApiModelProperty("计划单号")
    private String orderCode;

    @ExcelProperty(value = "检测工位", index = 2)
    @ApiModelProperty("检测工位")
    private String roleCode;

    @ExcelProperty(value = "客户代码", index = 3)
    @ApiModelProperty("客户代码")
    private String merchantCode;

    @ExcelProperty(value = "客户型号", index = 4)
    @ApiModelProperty("客户型号")
    private String merchantSpe;

    @ExcelProperty(value = "编码", index = 5)
    @ApiModelProperty("编码")
    private String benChangCode;

    @ExcelProperty(value = "质检状态", index = 6)
    @ApiModelProperty("质检状态")
    private String qualityStatus;

    @ExcelProperty(value = "故障编码", index = 7)
    @ApiModelProperty("故障编码")
    private String questionCode;

    @ExcelProperty(value = "故障描述", index = 8)
    @ApiModelProperty("故障描述")
    private String questionContent;

    @ExcelProperty(value = "维修内容", index = 9)
    @ApiModelProperty("维修内容")
    private String repairContent;

    @ExcelProperty(value = "创建时间", index = 10)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ExcelProperty(value = "更新时间", index = 11)
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
