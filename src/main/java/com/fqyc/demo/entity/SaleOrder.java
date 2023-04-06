package com.fqyc.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
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
@TableName("t_sale_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty(value = "计划单号")
    @ExcelProperty(value = "计划单号")
    private String orderCode;

    @ApiModelProperty("编码")
    @ExcelProperty(value = "编码")
    private String benChangCode;

    @ApiModelProperty("客户代码")
    @ExcelProperty(value = "客户代码")
    private String merchantCode;

    @ApiModelProperty("客户型号")
    @ExcelProperty(value = "客户型号")
    private String merchantSpe;

    @ApiModelProperty("数量")
    @ExcelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty("是否删除")
    private int isDeleted;

    private Date createTime;

    private Date updateTime;

}
