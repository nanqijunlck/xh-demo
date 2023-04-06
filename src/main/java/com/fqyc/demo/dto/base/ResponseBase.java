package com.fqyc.demo.dto.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author lck
 * @Date 2022/12/6 16:02
 * @Version 1.0
 * @Desc
 */
public class ResponseBase<T> {
    @ApiModelProperty("请求返回码，10000-成功，其他失败")
    private String code;
    @ApiModelProperty("请求返回信息")
    private String msg;
    @ApiModelProperty("请求返回实体对象")
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    private Long timestamp = Long.valueOf(System.currentTimeMillis());

    public boolean checkSuccess() {
        return ErrorType.SUCCESS.getCode().equals(this.code);
    }

    public static <T> ResponseBase<T> success() {
        ResponseBase<T> base = new ResponseBase();
        base.setCode(ErrorType.SUCCESS.getCode());
        base.setMsg(ErrorType.SUCCESS.getMsg());
        return base;
    }

    public static <T> ResponseBase<T> error(ErrorType type) {
        ResponseBase<T> base = new ResponseBase();
        base.setCode(type.getCode());
        base.setMsg(type.getMsg());
        return base;
    }

    public static <T> ResponseBase<T> error(String code, String msg) {
        ResponseBase<T> base = new ResponseBase();
        base.setCode(code);
        base.setMsg(msg);
        return base;
    }
}

