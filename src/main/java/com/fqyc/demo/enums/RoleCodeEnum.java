package com.fqyc.demo.enums;

/**
 * @author LY
 */
public enum RoleCodeEnum {

    ONE_SCAN_MACHINE("调试", "调试"),
    TWO_SCAN_MACHINE("老化", "老化"),
    THREE_SCAN_MACHINE("试焊", "试焊"),
    FORE_SCAN_MACHINE("综合测试", "综合测试"),
    FIVE_SCAN_MACHINE("维修", "维修");

    private String code;
    private String msg;

    RoleCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RoleCodeEnum valueOfKey(String code) {
        for (RoleCodeEnum temp : RoleCodeEnum.values()) {
            if (code.equals(temp.getCode())) {
                return temp;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
