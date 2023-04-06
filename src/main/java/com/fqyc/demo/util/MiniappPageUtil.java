package com.fqyc.demo.util;

/**
 * 小程序带参数统一存放
 *
 * @author liujianqiang
 * @create 2020-12-29 10:10
 **/

public class MiniappPageUtil {

    /**
     * 小程序带参数
     * @param page
     * @param merCode
     * @param empCode
     * @return
     */
    public static String appendMiniAppParam(String page, String merCode, String empCode, boolean isLiveCode){
        String miniPage;
        if(page.contains("?")){
            miniPage = page + "&merCode=" + merCode + "&empCode=" + empCode + "&from=welcome&fromChannel=share-e";
        }else {
            miniPage = page + "?merCode=" + merCode + "&empCode=" + empCode + "&from=welcome&fromChannel=share-e";
        }

        //是否需要携带上活码标志
        if(isLiveCode) {
            miniPage = miniPage + "&fromScene=liveCode";
        }
        return miniPage;
    }

    public static String appendMiniAppParam(String page, String merCode, String empCode){
        return appendMiniAppParam(page, merCode, empCode, Boolean.FALSE);
    }

}
