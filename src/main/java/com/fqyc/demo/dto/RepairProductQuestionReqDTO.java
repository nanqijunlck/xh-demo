package com.fqyc.demo.dto;

import com.fqyc.demo.dto.base.PageBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lck
 * @Date 2023/3/23 9:54
 * @Version 1.0
 * @Desc
 */
@Data
public class RepairProductQuestionReqDTO extends PageBase implements Serializable {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("故障编码")
    private String questionCode;

    @ApiModelProperty("故障内容")
    private String questionContent;

    @ApiModelProperty("维修编码")
    private String repairQuestionCode;

    @ApiModelProperty("维修内容")
    private String repairQuestionContent;
}
