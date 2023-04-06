package com.fqyc.demo.util;

import com.fqyc.demo.dto.base.ErrorType;
import com.fqyc.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author : Lee
 * @Create : 2021/6/7 14:16
 * @Description :
 */

@Slf4j
public class HoneyAssertUtil {

    public static void isTrue(boolean expression, String errorMsg) {
        if(!expression) {
            throw new BizException(ErrorType.OPERATOR_ERROR.getCode(), errorMsg);
        }
    }

    public static void isFalse(boolean expression, String errorMsg) {
        if(expression) {
            throw new BizException(ErrorType.OPERATOR_ERROR.getCode(), errorMsg);
        }
    }

    public static void notNull(Object value, String errorMsg) {
        if(Objects.isNull(value)) {
            throw new BizException(ErrorType.OPERATOR_ERROR.getCode(), errorMsg);
        }
    }

}
