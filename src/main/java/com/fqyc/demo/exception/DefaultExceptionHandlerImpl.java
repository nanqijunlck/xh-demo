package com.fqyc.demo.exception;//package com.fqyc.quality.exception;
//
// import com.alibaba.fastjson.JSON;
// import java.lang.reflect.Field;
// import java.util.Map;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import com.fqyc.quality.dto.ErrorType;
// import com.fqyc.quality.dto.ResponseBase;
// import com.fqyc.quality.util.ExLogger;
// import com.fqyc.quality.util.UUIDUtil;
// import org.apache.commons.lang3.StringUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//@ResponseBody
//public class DefaultExceptionHandlerImpl
//  implements IExceptionHandler
//{
//  static final String EXLOG_FIELD_0 = "error";
//  @Autowired
//  private ExceptionProperties exceptionProperties;
//
//  @ExceptionHandler({WarnException.class})
//  public Object handleWarnException(WarnException e, HttpServletRequest request, HttpServletResponse response)
//  {
////    sentinelTrace(request, e);
//    Object dto = e.getResponseDTO();
//    String message = e.getMessage();
//    if (dto == null) {
//      dto = buildResponseDTO(message, e.getTipMessage(), e.getCode());
//    }
//    return logIt(request, response, dto, e);
//  }
//
//  @ExceptionHandler({ErrorException.class})
//  public Object handleErrorException(ErrorException e, HttpServletRequest request, HttpServletResponse response)
//  {
////    sentinelTrace(request, e);
//    String message = e.getMessage();
//    Object dto = e.getResponseDTO();
//    if (dto == null) {
//      dto = buildResponseDTO(message, e.getTipMessage(), e.getCode());
//    }
//    return logIt(request, response, dto, e);
//  }
//
//  @ExceptionHandler({Exception.class})
//  public ResponseBase handleOtherException(Exception e, HttpServletRequest request, HttpServletResponse response)
//  {
////    sentinelTrace(request, e);
//    ResponseBase responseBase = buildResponseDTO();
//    return (ResponseBase)logIt(request, response, responseBase, e);
//  }
//
//  private <T> T logIt(HttpServletRequest request, HttpServletResponse response, T dto, Exception e)
//  {
//    if ((request == null) || (response == null) || (e == null)) {
//      return dto;
//    }
//    String uri = request.getRequestURI();
//    String url = request.getRequestURL().toString();
//    String message = e.getMessage();
//    if (message == null) {
//      message = JSON.toJSONString(dto);
//    }
//    String eid = buildExceptionId();
//    ExLogger exLogger = ExLogger.logger().field("error").field(uri).field(url).field(getDuration(request)).field(eid);
//    if ((e instanceof WarnException)) {
//      exLogger.warn(message, new Object[] { e });
//    } else {
//      exLogger.error(message, new Object[] { e });
//    }
//    if (StringUtils.isBlank(this.exceptionProperties.getEidTipFormat())) {
//      return dto;
//    }
//    try
//    {
//      if ((dto instanceof ResponseBase))
//      {
//        ResponseBase responseBase = (ResponseBase)dto;
//        String msg = responseBase.getMsg();
//        if (msg != null) {
//          responseBase.setMsg(appendExceptionId(msg));
//        }
//        return dto;
//      }
//      if ((dto instanceof Map))
//      {
//        String msg = (String)((Map)dto).get("msg");
//        if (msg != null) {
//          ((Map)dto).put("msg", appendExceptionId(msg));
//        }
//        return dto;
//      }
//      Field field = dto.getClass().getField("msg");
//      field.setAccessible(true);
//      String msg = (String)field.get(dto);
//      if (msg != null) {
//        field.set(dto, appendExceptionId(msg));
//      }
//    }
//    catch (Exception localException) {}
//    return dto;
//  }
//
//  private String appendExceptionId(String msg)
//  {
//    return msg + String.format(this.exceptionProperties.getEidTipFormat(), new Object[] { buildExceptionId() });
//  }
//
//  static String getDuration(HttpServletRequest request)
//  {
////    long start = TraceIdFilter.getStartTimestamp(request);
//    long start = System.currentTimeMillis();
//    if (start < 0L) {
//      return String.valueOf(start);
//    }
//    return String.valueOf(System.currentTimeMillis() - start);
//  }
//
////  private void sentinelTrace(HttpServletRequest request, Throwable t)
////  {
////    if (t == null) {
////      return;
////    }
////    Entry entry = (Entry)request.getAttribute("$$sentinel_spring_web_entry_attr");
////    if (entry == null) {
////      return;
////    }
////    Tracer.traceEntry(t, entry);
////  }
//
//  private ResponseBase buildResponseDTO(String message, String tipMessage, String code)
//  {
//    if (StringUtils.isBlank(tipMessage)) {
//      tipMessage = message;
//    }
//    if (StringUtils.isBlank(tipMessage)) {
//      tipMessage = this.exceptionProperties.getTipMessage();
//    }
//    if (StringUtils.isBlank(tipMessage)) {
//      tipMessage = ErrorType.DEFAULT_TIP_EXCEPTION_MESSAGE.getMsg();
//    }
//    ResponseBase dto = new ResponseBase();
//    dto.setCode(code);
//    dto.setMsg(tipMessage);
//    return dto;
//  }
//
//  private ResponseBase buildResponseDTO()
//  {
//    ResponseBase responseBase = ResponseBase.error(ErrorType.DEFAULT_TIP_EXCEPTION_MESSAGE);
//    if (StringUtils.isNotBlank(this.exceptionProperties.getTipMessage())) {
//      responseBase.setMsg(this.exceptionProperties.getTipMessage());
//    }
//    return responseBase;
//  }
//
//  private String buildExceptionId()
//  {
//    return UUIDUtil.generateShortId();
//  }
//}