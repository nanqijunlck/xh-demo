package com.fqyc.demo.exception;

 public class WarnException extends BaseException {
   public static class WarnExceptionBuilder
   {
     private String code;
     private String tipMessage;
     private String message;
     private Exception cause;
     private Object responseDTO;

     public String toString()
     {
       return "WarnException.WarnExceptionBuilder(code=" + this.code + ", tipMessage=" + this.tipMessage + ", message=" + this.message + ", cause=" + this.cause + ", responseDTO=" + this.responseDTO + ")";
     }

     public WarnException build()
     {
       return new WarnException(this.code, this.tipMessage, this.message, this.cause, this.responseDTO);
     }

     public WarnExceptionBuilder responseDTO(Object responseDTO)
     {
       this.responseDTO = responseDTO;return this;
     }

     public WarnExceptionBuilder cause(Exception cause)
     {
       this.cause = cause;return this;
     }

     public WarnExceptionBuilder message(String message)
     {
       this.message = message;return this;
     }

     public WarnExceptionBuilder tipMessage(String tipMessage)
     {
       this.tipMessage = tipMessage;return this;
     }

     public WarnExceptionBuilder code(String code)
     {
       this.code = code;return this;
     }
   }

   public static WarnExceptionBuilder builder()
   {
     return new WarnExceptionBuilder();
   }

   public WarnException(String code, String tipMessage, String message, Exception cause, Object responseDTO)
   {
     super(code, tipMessage, message, cause, responseDTO);
   }
 }