package com.fqyc.demo.util;

public class ImageUtils {

    public static String processMiddleImage(String prefix, String relativeImageUrl) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(relativeImageUrl)) {
            return null;
        }
        if (relativeImageUrl.startsWith("http")) {
            return relativeImageUrl;
        }
        return String.format("%s%s", prefix, relativeImageUrl);
    }
}
