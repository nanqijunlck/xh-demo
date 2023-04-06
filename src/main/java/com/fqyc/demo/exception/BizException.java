package com.fqyc.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务处理异常类
 *
 * @Author: frank
 * @Date: 2019年6月3日 上午11:00:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

    private String errorCode;

    public BizException(String errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode) {
        super("");
        this.errorCode = errorCode;
    }

}
