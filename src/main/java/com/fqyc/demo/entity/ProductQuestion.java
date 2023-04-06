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
@TableName("t_product_question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty("质检故障编码")
    private String questionCode;

    @ApiModelProperty("质检故障内容")
    private String questionContent;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("维修记录对应的故障编码")
    private String repairQuestionCode;

    @ApiModelProperty("是否删除")
    private int isDeleted;

    private Date createTime;

    private Date updateTime;

}
