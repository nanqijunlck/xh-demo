package com.fqyc.demo.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * zenglu 2020-11-18
 */
public class KeyUtils {

    /**
     * 工具方法。前缀+多参数生成 key
     *
     * @param bizPrefix 业务前缀
     * @param params    参数
     * @return key
     */
    public static String generateBizKey(String bizPrefix, Object... params) {
        if (params == null || params.length <= 0) {
            return bizPrefix;
        }
        List<String> list = Lists.newArrayList();
        for (Object param : params) {
            list.add(param.toString());
        }
        return String.format("%s:%s", bizPrefix, String.join("_", list));
    }

//    public static String genTraceId() {
//        String traceId = TraceContext.traceId();
//        if (com.honey.common.util.StringUtils.isEmpty(traceId)) {
//            traceId = SnowflakeIdWorker.generateId() + "";
//        }
//        return traceId;
//    }

    public static String genTokenAndSecret(int len) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890_-";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
