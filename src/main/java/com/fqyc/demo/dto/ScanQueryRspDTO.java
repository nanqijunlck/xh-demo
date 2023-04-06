package com.fqyc.demo.dto;

import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.QualityOrder;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class ScanQueryRspDTO implements Serializable {

    @ApiModelProperty("二维码")
    private String qrCode;

    @ApiModelProperty("是否可以编辑 true/false")
    private Boolean editFunction;

    @ApiModelProperty("质检记录集合")
    private List<QualityOrder> qualityOrderList;

    @ApiModelProperty("质检故障集合")
    private List<ProductQuestion>  productQuestionList;

}
