
package com.fqyc.demo.aspect;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.dto.base.ErrorType;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;
import java.util.stream.Collectors;

/**
 * 全局异常处理类，每个方法可以根据需要添加Servlet对象，Spring会自动注入
 * - HttpServletRequest/HttpServletResponse/HttpSession/...
 *
 * @author panyi
 * @date 2020-03-19 10:37
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private ExceptionCode exceptionCode;

    /**
     * 参数异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, InvalidFormatException.class})
    @ResponseBody
    public ResponseBase<Null> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        String errInfo = String.format(exceptionCode.getExceptionMsg(ExceptionCodeConstants.ARGUMENT_ERR), e.getMessage());
        ResponseBase<Null> responseBase = ResponseBase.error(ExceptionCodeConstants.ARGUMENT_ERR, errInfo);
        log.error(errInfo, e);
        return responseBase;
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public ResponseBase<Null> bizExceptionHandler(BizException e) {
        String errInfo = null;
        String definedExceptionMsg = exceptionCode.getExceptionMsg(e.getErrorCode());
        if (!StringUtils.isEmpty(definedExceptionMsg)) {
            errInfo = String.format(exceptionCode.getExceptionMsg(e.getErrorCode()), e.getMessage());
        } else if (!StringUtils.isEmpty(e.getMessage())) {
            errInfo = e.getMessage();
        } else {
            errInfo = "未定义系统故障，请联系管理员";
        }
        log.error(errInfo, e);
        return ResponseBase.error(e.getErrorCode(), errInfo);
    }

    /**
     * 处理异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseBase<Null> exceptionHandler(Exception e) {
        String errInfo = String.format(exceptionCode.getExceptionMsg(ExceptionCodeConstants.INTERNEL_ERR), e.getMessage());
        ResponseBase<Null> responseBase = ResponseBase.error(ExceptionCodeConstants.INTERNEL_ERR, errInfo);
        log.error(errInfo, e);
        return responseBase;
    }

    /**
     * 参数自动校验异常
     *
     * @param e the e
     * @return response
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    @ResponseBody
    public ResponseBase<Null> handleValidationException(Exception e) {
        String msg;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else {
            msg = "必填参数缺失";
        }
        log.warn("参数校验不通过, msg: {}", msg);
        return ResponseBase.error(ErrorType.PARA_ERROR.getCode(), msg);
    }

    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }

    /**
     * 处理异常 IllegalStateException
     *
     * @param e IllegalStateException
     * @return void
     */
    @ExceptionHandler({IllegalStateException.class})
    @ResponseBody
    public ResponseBase<Null> exceptionHandler(IllegalStateException e) {
        String errInfo = e.getMessage();
        ResponseBase<Null> responseBase = ResponseBase.error(ExceptionCodeConstants.INTERNEL_ERR, errInfo);
        log.error(errInfo, e);
        return responseBase;
    }


}
