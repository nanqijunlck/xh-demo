package com.fqyc.demo.exception;

/**
 * @Classname SdpErrorCodeEnum
 * @Description 小蜜业务服务错误码定义
 * @Date 2020/9/28 11:07
 * @Created by Jack
 */
public enum BizErrorCodeEnum {
    SUCCESS("10000", "操作成功"),
    ERROR("99999", "系统错误"),

    REQUEST_PARAMS_ERROR("55555","请求参数不合法!"),
    USER_NOT_LOGIN("10001","用户未登录，请先登录！"),
    GOODS_DATA_NOT_EXISTS("10002","查询商品服务，获取商品信息数据不存在！"),
    GET_EMP_INFO_NOT_EXISTS("10003","获取专属顾问信息不存在"),
    WXCORPID_NOT_EXISTS("10004","企业微信公司id不存在"),
    TASK_RULE_NOT_EXISTS("10005","任务规则不存在"),
    GETEWXMERREL_NOT_EXISTS("10006","查询企业微信绑定关系不存在"),
    ;

    private String code;
    private String msg;


    BizErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
