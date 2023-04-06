package com.fqyc.demo.dto.base;

/**
 * @Author lck
 * @Date 2022/12/6 16:03
 * @Version 1.0
 * @Desc
 */

public enum ErrorType {
    SUCCESS("10000", "操作成功"), OPERATOR_ERROR("20000", "操作失败"), PARA_ERROR("20001", "请求参数错误"), RECORD_NOT_EXISTS("20002", "记录不存在"), STATUS_ERROR("20003", "状态不匹配"), RECORD_EXISTS("20004", "记录已存在"), PHONE_EXISTS("20005", "手机号已存在"), STORE_CANT_ADD_SUB("20006", "门店不能添加门店"), DATA_ERROR("20007", "数据错误"), CREATE_ORG_ERROR("30001", "创建组织机构失败"), UPDATE_ORG_ERROR("30002", "创建组织机构失败"), SERVICE_TEMP_ERROR("30003", "程序开小差了，稍等片刻后请刷新页重试"), DEFAULT_TIP_EXCEPTION_MESSAGE("50000", "系统异常"), EXCEL_ROW_NULL("40002", "导出的记录为空"), EXCEL_ERROR("40003", "导出EXCEL异常"), CONNECTED_ERROR("50000", "连接服务异常"), COMMIT_DUPLICATE("10002", "重复提交"), BLOCKING("10003", "抱歉, 请求过多触发流控，请稍后重试"), BLOCKING_FLOW("10003", "抱歉, 请求过多触发流控，请稍后重试"), BLOCKING_DEGRADE("10004", "抱歉, 请求过多触发熔断，请稍后重试"), BLOCKING_SYSTEM("10005", "抱歉, 系统繁忙，请稍后重试");

    private String code;
    private String msg;

    private ErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}