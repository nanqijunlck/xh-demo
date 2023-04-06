package com.fqyc.demo.aspect.annotation;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
///**
// * 方法执行前后打日志。默认 debug
// */
//@Aspect
//@Slf4j
//@Component
//public class LogMethodAspect {
//
//    @Pointcut("@annotation(cn.hydee.ewx.honey.aspect.annotation.LogMethod)")
//    public void logMethodAnnotation() {
//    }
//
//    @Around("logMethodAnnotation() && @annotation(logMethod)")
//    public Object sysExceptionCatchAndProcess(ProceedingJoinPoint joinPoint, LogMethod logMethod) throws Throwable {
//        this.logBeforeProceed(joinPoint, logMethod);
//        Object result = joinPoint.proceed();
//        this.logAfterProceed(joinPoint, logMethod, result);
//        return result;
//    }
//
//    /**
//     * 执行前，对参数进行记录
//     *
//     * @param joinPoint j
//     * @param logMethod l
//     */
//    private void logBeforeProceed(ProceedingJoinPoint joinPoint, LogMethod logMethod) {
//        try {
//            StringBuilder logBuilder = new StringBuilder();
//            // 方法
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            logBuilder.append("方法参数日志。方法：");
//            this.appendMethodPath(logBuilder, methodSignature);
//            // 参数名,参数值
//            String[] argNames = methodSignature.getParameterNames();
//            Object[] args = joinPoint.getArgs();
//            if (args != null && args.length > 0) {
//                logBuilder.append("参数:");
//                for (int i = 0; i < argNames.length; i++) {
//                    logBuilder.append(argNames[i]).append("=").append(JSONObject.toJSONString(args[i]));
//                }
//            }
//            log.debug(logBuilder.toString());
//        } catch (Exception e) {
//            log.error("logBeforeProceed e=", e);
//        }
//    }
//
//    /**
//     * 执行后 对返回值进行记录
//     *
//     * @param joinPoint j
//     * @param logMethod l
//     * @param result    r
//     */
//    private void logAfterProceed(ProceedingJoinPoint joinPoint, LogMethod logMethod, Object result) {
//        try {
//            StringBuilder logBuilder = new StringBuilder();
//            // 方法
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            logBuilder.append("方法返回日志。方法：");
//            this.appendMethodPath(logBuilder, methodSignature);
//            logBuilder.append("返回=").append(JSONObject.toJSONString(result));
//            log.debug(logBuilder.toString());
//        } catch (Exception e) {
//            log.error("logAfterProceed e=", e);
//        }
//    }
//
//    /**
//     * 通用 方法名处理
//     *
//     * @param stringBuilder   s
//     * @param methodSignature m
//     */
//    private void appendMethodPath(StringBuilder stringBuilder, MethodSignature methodSignature) {
//        stringBuilder.append(methodSignature.getMethod().getDeclaringClass().toString())
//                .append(".")
//                .append(methodSignature.getMethod().getName())
//                .append(" ");
//    }
//}
