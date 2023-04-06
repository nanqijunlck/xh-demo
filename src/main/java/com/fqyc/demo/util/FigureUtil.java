package com.fqyc.demo.util;

import java.math.BigDecimal;

/**
 * @author LY
 */
public class FigureUtil {

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    public static final String CURRENCY_YUAN_CH_REGEX = "元";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i,
                        intString.length() - i + 1));
            }
            result.reverse().append(".")
                    .append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) throws Exception {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.valueOf(amount))
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static Object changeF2YByInt(Integer amount, String unit) {
        if (CURRENCY_YUAN_CH_REGEX.equals(unit)) {
            return BigDecimal.valueOf(Long.valueOf(amount))
                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        }
        return amount;
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(Long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
                .toString();
    }

    public static String changeY2FByStr(String amount) {
        BigDecimal b = new BigDecimal(amount).multiply(new BigDecimal(100)).setScale(0);
        return b.toPlainString();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        // 处理包含, ￥
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");
        // 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
                    ".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
                    ".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
                    ".", "") + "00");
        }
        return amLong.toString();
    }

    public static BigDecimal changeF2Y(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }
}
