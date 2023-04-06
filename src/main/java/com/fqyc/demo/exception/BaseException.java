package com.fqyc.demo.exception;

/**
 * @Author lck
 * @Date 2022/12/6 19:32
 * @Version 1.0
 * @Desc
 */
class BaseException extends RuntimeException {
    private String code;
    private String tipMessage;
    private Object responseDTO;

    BaseException(String code, String tipMessage, String message, Throwable cause) {  }

    BaseException(String code, String tipMessage, String message, Throwable cause, Object responseDTO) { }

    public void setCode(String code) { this.code = code; }

    public void setTipMessage(String tipMessage) { this.tipMessage = tipMessage; }

    public void setResponseDTO(Object responseDTO) { this.responseDTO = responseDTO; }

    public String getCode() { return this.code; }

    public String getTipMessage() { return this.tipMessage; }

    public Object getResponseDTO() { return this.responseDTO; }
}
