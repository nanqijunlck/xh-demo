package com.fqyc.demo.util;//package com.fqyc.quality.util;
//
///**
// * @author lck
// * @date 2020-07-01 16:46
// * @since 1.0
// */
//public class BizLogUtil {
//    public static void info(String traceId, String type, String bizId, String content) {
//        BizLogService bizLogService = SpringUtils.getBean(BizLogService.class);
//        bizLogService.insert(traceId, type, BizLog.LEVEL_INFO, bizId, content);
//    }
//
//    public static void debug(String traceId, String type, String bizId, String content) {
//        BizLogService bizLogService = SpringUtils.getBean(BizLogService.class);
//        bizLogService.insert(traceId, type, BizLog.LEVEL_DEBUG, bizId, content);
//    }
//
//    public static void error(String traceId, String type, String bizId, String content) {
//        BizLogService bizLogService = SpringUtils.getBean(BizLogService.class);
//        bizLogService.insert(traceId, type, BizLog.LEVEL_ERROR, bizId, content);
//    }
//}
