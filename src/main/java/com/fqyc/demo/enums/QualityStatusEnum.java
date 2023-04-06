package com.fqyc.demo.enums;

/**
 * @author LY
 */
public enum QualityStatusEnum {

    SUCCESS("通过", "通过"),
    ERROR("不通过", "不通过")
   ;

    private String code;
    private String msg;

    QualityStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static QualityStatusEnum valueOfKey(String code) {
        for (QualityStatusEnum temp : QualityStatusEnum.values()) {
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
