package com.fqyc.demo.aspect;
//
////import com.fqyc.quality.aspect.annotation.SysExceptionCatcher;
//import com.fqyc.quality.exception.BizException;
//import com.fqyc.quality.exception.ExceptionCode;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Aspect
//@Slf4j
//@Component
//public class SysExceptionCatchAspect {
//
//    @Resource
//    private ExceptionCode exceptionCode;
//
//    @Pointcut("@annotation(cn.hydee.ewx.honey.aspect.annotation.SysExceptionCatcher)")
//    public void sysExceptionCatcherAnnotation() {
//
//    }
//
//    @Around("sysExceptionCatcherAnnotation() && @annotation(sysExceptionCatcher)")
//    public Object sysExceptionCatchAndProcess(ProceedingJoinPoint joinPoint, SysExceptionCatcher sysExceptionCatcher) throws Throwable {
//        log.debug("正在经过切片增强。enhanceDayDayUp");
//        try {
//            return joinPoint.proceed();
//        } catch (BizException bizE) {
//            log.debug("业务异常BizException,e=", bizE);
//            throw bizE;
//        } catch (Exception e) {
//            log.error("业务代码未捕获的异常,e=", e);
//            String errCode = sysExceptionCatcher.errorCode();
//            throw new BizException(errCode);
//        }
//    }
//}
