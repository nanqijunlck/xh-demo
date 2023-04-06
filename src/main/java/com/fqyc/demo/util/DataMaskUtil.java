package com.fqyc.demo.util;


/**
 * @author QYP
 * @description: 数据脱敏工具类
 * @date 2021/3/8 11:22
 */

public class DataMaskUtil {

    private static final String SYMBOL = "*";
    private static final int MOBILE_LENGTH = 11;
    private static final int IDCARD_LENGTH = 18;

    /**
     * @author QYP
     * @date 2021/3/8 11:34
     * @description: 手机号身份证  保留前三位  后四位
     */
    public static String processMobileAndIdCard(String data) {
        if (data == null) {
            return null;
        }
        int len = data.length();
        // 不满足手机号身份证号的格式  不做处理
        if (len == MOBILE_LENGTH || len == IDCARD_LENGTH) {
            return dataMask(data, 3, 4);
        }
        return data;
    }

    /**
     * @param head 前面保留几位
     * @param end  后面保留几位
     * @author QYP
     * @date 2021/3/8 13:36
     */
    public static String dataMask(String data, int head, int end) {
        if (data == null) {
            return null;
        }
        int len = data.length();
        if (head < 0 || head > len || end < 0 || end > len) {
            return data;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data, 0, head);
        for (int i = 0; i < len - head - end; i++) {
            stringBuilder.append(SYMBOL);
        }
        stringBuilder.append(data, len - end, len);
        return stringBuilder.toString();
    }

}
