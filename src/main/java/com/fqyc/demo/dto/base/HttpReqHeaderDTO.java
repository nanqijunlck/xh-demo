package com.fqyc.demo.dto.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 封装Http请求的Header中的业务属性
 *
 * @author panyi
 * @date 2020-03-19 10:05
 * @since 1.0
 */
@Data
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpReqHeaderDTO {
    @ApiModelProperty(value = "商户平台userId，从网关写入")
    @JSONField(name = "userId")
    private String userId;

    @ApiModelProperty(value = "traceId，由框架生成")
    @JSONField(name = "traceId")
    private String traceId;
}
