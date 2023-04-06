package com.fqyc.demo.exception;

import lombok.Data;
import lombok.ToString;

/**
 * 小蜜业务异常中心
 *
 * @author jack
 * @version 1.0
 * @date 2020/9/28 11:05
 */
@Data
@ToString
public class BizServiceException extends RuntimeException {
    /**
     * 错误枚举
     */
    private String code;
    /**
     * 错误描述
     */
    private String msg;

    public BizServiceException() {
        super();
    }

    public BizServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizServiceException(BizErrorCodeEnum paymentErrorCodeEnum) {
        this.code = paymentErrorCodeEnum.getCode();
        this.msg = paymentErrorCodeEnum.getMsg();
    }
}
