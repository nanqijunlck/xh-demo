package com.fqyc.demo.exception;

 public class ErrorException
   extends BaseException
 {
   public static class ErrorExceptionBuilder
   {
     private String code;
     private String tipMessage;
     private String message;
     private Throwable cause;
     private Object responseDTO;

     public String toString()
     {
       return "ErrorException.ErrorExceptionBuilder(code=" + this.code + ", tipMessage=" + this.tipMessage + ", message=" + this.message + ", cause=" + this.cause + ", responseDTO=" + this.responseDTO + ")";
     }

     public ErrorException build()
     {
       return new ErrorException(this.code, this.tipMessage, this.message, this.cause, this.responseDTO);
     }

     public ErrorExceptionBuilder responseDTO(Object responseDTO)
     {
       this.responseDTO = responseDTO;return this;
     }

     public ErrorExceptionBuilder cause(Throwable cause)
     {
       this.cause = cause;return this;
     }

     public ErrorExceptionBuilder message(String message)
     {
       this.message = message;return this;
     }

     public ErrorExceptionBuilder tipMessage(String tipMessage)
     {
       this.tipMessage = tipMessage;return this;
     }

     public ErrorExceptionBuilder code(String code)
     {
       this.code = code;return this;
     }
   }

   public static ErrorExceptionBuilder builder()
   {
     return new ErrorExceptionBuilder();
   }

   public ErrorException(String code, String tipMessage, String message, Throwable cause, Object responseDTO)
   {
     super(code, tipMessage, message, cause, responseDTO);
   }
 }