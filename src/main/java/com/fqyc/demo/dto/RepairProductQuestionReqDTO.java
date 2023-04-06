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

    @ApiModelProperty("维修编码")
    private String questionCode;

    @ApiModelProperty("维修内容")
    private String questionContent;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("维修记录对应的故障编码")
    private String repairQuestionCode;
}
