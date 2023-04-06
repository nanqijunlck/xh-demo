package com.fqyc.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @Author : Lee
 * @Create : 2021/9/14 14:58
 * @Description :
 */
public class Md5Utils {

    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public Md5Utils() {
    }

    public static String encode(String origin) {
        return encode(origin, "UTF-8");
    }

    public static String encode(String origin, String charset) {
        String result = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = new String(encodeHex(charset != null && !"".equals(charset) ? md.digest(origin.getBytes(charset)) : md.digest(origin.getBytes())));
        } catch (Exception var4) {
            log.error("md5 encode exception:{}", var4.getMessage(), var4);
        }

        return result;
    }

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }

        return chars;
    }

}
