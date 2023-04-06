package com.fqyc.demo.enums;

/**
 * @Classname ErrorMsgEnum
 * @Description 运行时异常消息提醒枚举设置
 * <li>errorKey=服务类名.方法名</li>
 * <li>errorMsg=具体方法运行时异常提示配置</li>
 * <li>errorCode=服务方法运行时异常错误码配置</li>
 * @Date 2020/11/18 15:06
 * @Created by Jack
 */
public enum ErrorMsgEnum {

    EmployeeComponent_queryEmpInfoByUnionid("EmployeeComponent.queryEmpInfoByUnionid", "获取员工信息异常", "80001"),
    ;


    private String errorKey;
    private String errorCode;
    private String errorMsg;

    ErrorMsgEnum(String errorKey, String errorMsg, String errorCode) {
        this.errorKey = errorKey;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public static ErrorMsgEnum valueOfKey(String key) {
        for (ErrorMsgEnum temp : ErrorMsgEnum.values()) {
            if (key.equals(temp.getErrorKey())) {
                return temp;
            }
        }
        return null;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
