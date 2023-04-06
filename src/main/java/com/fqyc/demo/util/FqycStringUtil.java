package com.fqyc.demo.util;

import com.fqyc.demo.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字符串操作工具类
 *
 * @author stiven_wu
 */
@Slf4j
public class FqycStringUtil {

    private static String STORE_LIVE_CODE_REGEX = "^" + GlobalConstants.STORE_LIVE_CODE + "\\d+$";

    /**
     * 字符串分割
     *
     * @param ori      字符串   STORE_LIVE_CODE:3
     * @param splitStr 分割符
     * @param index    取第几位
     * @return
     */
    public static String split(String ori, String splitStr, int index) {
        try {
            Pattern pattern = Pattern.compile(STORE_LIVE_CODE_REGEX);
            Matcher matcher = pattern.matcher(ori);
            if (matcher.matches()) {
                return Stream.of(ori.split(splitStr)).collect(Collectors.toList()).get(index);
            }
        } catch (Exception e) {
            log.error("解析字符串出现错误 {}", ori);
            return "-1";
        }
        return "-1";
    }

    /**
     * ori 字符串中 第0位是否与 compareStr 相同
     *
     * @param ori
     * @param splitStr
     * @param compareStr
     * @return
     */
    public static boolean isEq(String ori, String splitStr, String compareStr) {
        if (StringUtils.isEmpty(ori)) {
            return false;
        }
        try {
            String stateFlag = FqycStringUtil.split(ori, splitStr, 0);
            if ((stateFlag + splitStr).equals(compareStr)) {
                return true;
            }
        } catch (Exception e) {
            log.error("处理声明字段时出现异常 {}", ori);
            return false;
        }
        return false;
    }
}
