package com.fqyc.demo.controller.base;

import com.fqyc.demo.constants.GlobalConstants;
import com.fqyc.demo.dto.base.HttpReqHeaderDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.enums.ErrorMsgEnum;
import com.fqyc.demo.exception.BizErrorCodeEnum;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.exception.BizServiceException;
import com.fqyc.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * @author panyi
 * @date 2020-03-17 18:44
 * @since 1.0
 */
@Slf4j
public class BaseController extends AbstractController {


    @Autowired
    private UserService loginService;

    /**
     * 读取http请求头信息
     *
     * @return HttpReqHeaderDTO
     */
    public HttpReqHeaderDTO getHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpReqHeaderDTO httpReqHeaderDTO = HttpReqHeaderDTO.builder()
                .userId(request.getHeader(GlobalConstants.HeaderAttr.USERID)).build();
        return httpReqHeaderDTO;
    }

    /**
     * 从请求头中获取用户的userId，如果用户未登录，返回null
     *
     * @return empCode
     */
    public String getUserId() {
        HttpReqHeaderDTO httpReqHeaderDTO = getHeader();
        return StringUtils.isEmpty(httpReqHeaderDTO.getUserId()) ? null : httpReqHeaderDTO.getUserId();
    }

    /**
     * 从缓存中读取用户登录信息
     *
     * @return UserInfoDTO, 如果缓存中未获取到，则抛出异常
     */
    public UserInfo getLoginUserInfo() {

        String userId = getUserId();
        if (userId == null || "".equalsIgnoreCase(userId)) {
            return null;
        }
        return loginService.getLoginUserInfo(Integer.valueOf(userId));
    }


    protected void paramsCheckValid(BindingResult result) {
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new BizServiceException(BizErrorCodeEnum.REQUEST_PARAMS_ERROR.getCode(), message);
        }
    }

    /***
     * 通用异常处理
     * @param service 目标服务
     * @param method  目标方法
     * @param input 目标方法入参
     * @param <T> 目标服务返回参数
     * @return
     */
    protected <T> ResponseBase<T> process(Object service, String method, Object input, BindingResult bindingResult) {
        ResponseBase<T> responseBase = new ResponseBase<T>();
        try {
            if (null != bindingResult) {
                paramsCheckValid(bindingResult);
            }
            T t = null;
            try {
                t = (T) service.getClass().getMethod(method, input.getClass()).invoke(service, input);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
            responseBase.setCode(BizErrorCodeEnum.SUCCESS.getCode());
            responseBase.setMsg(BizErrorCodeEnum.SUCCESS.getMsg());
            responseBase.setData(t);
        } catch (BizException bizE) {
            log.info("invoke service={} bizError={}", service, bizE);
            responseBase.setCode(bizE.getErrorCode());
            responseBase.setMsg(bizE.getMessage());
        } catch (BizServiceException e) {
            log.info("invoke service={} bizError={}", service, e);
            responseBase.setCode(e.getCode());
            responseBase.setMsg(e.getMsg());
        } catch (Throwable t) {
            log.error("invoke service{} error:{}", service, t);
            getExceptionErrorMsg(service, method, responseBase);
        }
        return responseBase;
    }

    /**
     * 获取具体业务运行时异常消息
     *
     * @param service 目标服务
     * @param method  目标方法
     * @return 业务运行时异常消息
     */
    private void getExceptionErrorMsg(Object service, String method, ResponseBase responseBase) {
        String key = service.getClass().getSimpleName().concat(".").concat(method);
        ErrorMsgEnum errorMsgEnum = ErrorMsgEnum.valueOfKey(key);
        if (null == errorMsgEnum) {
            responseBase.setMsg(BizErrorCodeEnum.ERROR.getMsg());
            responseBase.setCode(BizErrorCodeEnum.ERROR.getCode());
        } else {
            responseBase.setMsg(errorMsgEnum.getErrorMsg());
            responseBase.setCode(errorMsgEnum.getErrorCode());
        }
    }

}
