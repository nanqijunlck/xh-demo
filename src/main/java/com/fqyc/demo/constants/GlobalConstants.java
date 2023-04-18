package com.fqyc.demo.constants;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.ehcache.xml.model.ConfigType;

import java.util.List;
import java.util.Map;

/**
 * 全局常量类
 *
 * @author lck
 * @date 2020-03-24 14:04
 * @since 1.0
 */
public class GlobalConstants {

    public static final String DISLOCK_PREFIX = "honey:";

    public static final Integer LOG_DAY = 3;

    public static final String STORE_DAILY_KEY = "STORE_DAILY_KEY";

    /**
     * 小数位精确度
     */
    public static final int PRECISION = 4;

    public static final int TYPE_ALL_FLAG = 1;

    public static final int TYPE_PART_FLAG = 2;

    public static final String PLAN_TYPE_FLAG_EIGHT = "EIGHT_OF_PLAN";

    public static final String PLAN_TYPE_FLAG_EVERY_FIVE = "FIVE_EVERY_PLAN";

    /**
     * http请求头header中的属性
     */
    public static class HeaderAttr {
        public static final String USERID = "userId";
    }

    public static class CacheNames {
        /*商户信息缓存名称*/
        public static final String MERCHANT = "merchant";
        /**
         * 病例缓存名称
         */
        public static final String CASE = "case";
    }

    /**
     * redis前缀
     */
    public static class RedisPrefix {
        /*登录用户信息*/
        public static final String USER_TOKEN = "user:token:";

        public static final String CASE_TOKEN = "case:token:";

        /*客户信息*/
        public static final String CUSTOMER_INFO = "customer:info";

        /*商户信息*/
        public static final String MERCHANT_USER_INFO = "merchant:info";

        /*用户首次登录标记*/
        public static final String USER_FIRST_TOKEN = "user:token:first:";

        /*企业微信查询员工 今日联系、今日客户等数据*/
        public static final String EMPLOYEE_WX_INFO = "employee:wxInfo";

        /*病例*/
        public static final String CASE = "CASE_";

        /*回访病例*/
        public static final String REVIEW_CASE = "REVIEW_CASE_";

        /*手机验证码*/
        public static final String SMS_CODE = "register_sms_";

        /*用券提醒*/
        public static final String COUPON_REMIND = "coupon_remind";

        /*员工优惠券*/
        public static final String EMP_COUPON = "emp_coupon";

        /*活动分享*/
        public static final String ACTIVITY_SHARE = "activity_share";

        /*优惠券页面*/
        public static final String COUPON_PAGE = "coupon_page";

        /*商户小程序appid*/
        public static final String MERCHANT_APP_ID = "merchant_app_id";

        /**
         * 欢迎语小程序配置的封面图media_id，企业微信端三天有效
         */
        public static final String WELCOME_MINIAPP_COVER = "welcome_miniapp_cover_";

        public static final String Plan_Media_Key = "groupsop:plan:media_key";

        /**
         * 企业群发，图片或者视频临时素材media_id redis key前缀
         */
        public static final String GROUP_MSG_MEDIA_KEY = "group:msg:media_key";

        /**
         * 企业群发，图片或者视频临时素材media_id redis key前缀
         */
        public static final String GROUP_MSG_REMIND_KEY = "group:msg:remind_key";

        /**
         * 群sop预取方案数量
         */
        public static final String GROUP_SOP_PRE_PLAN_COUNT = "groupsop:plan:count";
        /**
         * 群sop预取方案列表
         */
        public static final String GROUP_SOP_PRE_PLAN_LIST = "groupsop:plan:list";

        /**
         * 统计幂等KEY
         */
        public static final String STAT_STORE_DATE = "stat:store:set";

        public static final String STAT_EMP_RETYR = "stat_emp_retyr";

        public static final String STAT_STORE_RETYR = "stat_store_retyr";
        /**
         * 员工受益
         */
        public static final String EMP_COMMISSION = "emp:commission";
        /**
         * 员工业绩
         */
        public static final String EMP_SALE = "emp:sale";

        /**
         * 流失客户开关标识
         */
        public static final String DRAIN_TOGGLE = "ewx_honey_drain:";

        /**
         * 客户群群主机构树标志
         */
        public static final String GROUP_CHAT_ORG_TREE = "group_chat_org_tree:";

    }

    /**
     * redis key 生成类
     */
    public static class RedisKeysGenerator {
        /**
         * 登录用户信息缓存key
         */
        public static final String getUserTokenKey(String userId) {
            return RedisPrefix.USER_TOKEN + userId;
        }

        /**
         * 用户首次登录标记缓存key
         */
        public static final String getUserFirstTokenKey(String userId) {
            return RedisPrefix.USER_FIRST_TOKEN + userId;
        }

        /**
         * 商户信息缓存key
         */
        public static final String getMerchantKey(String userId) {
            return RedisPrefix.MERCHANT_USER_INFO + userId;
        }

        /**
         * 客户信息缓存key
         */
        public static final String getCustomerKey(Long id) {
            return RedisPrefix.CUSTOMER_INFO + id;
        }

        /**
         * 企业微信查询员工 今日联系、今日客户等数据缓存key
         */
        public static final String getEmployeeWxInfoKey(String merCode, String empCode, String day) {
            return RedisPrefix.EMPLOYEE_WX_INFO + merCode + empCode + day;
        }

        /**
         * 病例模板详情缓存key
         */
        public static final String getCaseDetailKey(String code) {
            return RedisPrefix.CASE + code;
        }

        /**
         * 回访病例模板详情缓存key
         */
        public static final String getReviewCaseDetailKey(String code) {
            return RedisPrefix.REVIEW_CASE + code;
        }

        /**
         * 手机验证码缓存key
         */
        public static final String getSmsCodeKey(String mobile) {
            return RedisPrefix.SMS_CODE + mobile;
        }

        public static final String getWelcomeMiniCoverKey(String merCode, Integer id, String md5Value) {
            return RedisPrefix.WELCOME_MINIAPP_COVER + merCode + "_" + id + "_" + md5Value;
        }


        public static final String getGroupSopPlanCount(Integer pushDate) {
            return RedisPrefix.GROUP_SOP_PRE_PLAN_COUNT + ":" + pushDate;
        }

        public static final String getGroupSopPlanList(Integer pushDate, Integer currentPage, Integer pageSize) {
            return RedisPrefix.GROUP_SOP_PRE_PLAN_LIST + ":" + pushDate + ":" + currentPage + ":" + pageSize;
        }

        public static final String getEmpCommissionKey(String merCode, String userId, int day) {
            return RedisPrefix.EMP_COMMISSION + "_" + merCode + "_" + userId + "_" + day;
        }

        public static String getEmpSaleKey(String merCode, String userId, int day) {
            return RedisPrefix.EMP_SALE + "_" + merCode + "_" + userId + "_" + day;
        }

        public static String getGroupChatTree(String merCode) {
            return RedisPrefix.GROUP_CHAT_ORG_TREE + "_" + merCode;
        }

    }

    /**
     * 任务状态
     */
    public static class TaskStatus {
        //待处理
        public static final Integer PENDING = 0;
        //已处理
        public static final Integer PROCESSED = 1;
        //回访任务创建/执行失败
        public static final Integer CREATE_OR_FAIL = 0;
        //回访任务执行成功
        public static final Integer REVIEW_TASK_SUCC = 1;

    }

    /**
     * 任务类型:1-办卡任务；2-用药建议；3-病情建档；4-回访任务；5-健康咨询；
     */
    public static class TaskType {
        //1-办卡任务
        public static final int APPLYCARD = 1;
        //2-活动转发
        public static final int ACTIVITY = 2;
        //2-活动转发
        public static final int MEDICINESUGGESTED = 2;
        //3-病情建档
        public static final int ARCHIVING = 3;
        //4-回访任务
        public static final int REVIEWTASKNUM = 4;
        //5-健康咨询
        public static final int HEALTH_CONSULTATION = 5;

    }

    /**
     * MQ的tag
     */
    public static class MQ {
        // 员工添加新外部联系人
        public static final String TAG_EMP_ADD_CONTACT = "EMP_ADD_CONTACT";
        // 员工离职
        public static final String TAG_EMP_LEAVE_JOB = "EMP_LEAVE_JOB";
        // 员工离职
        public static final String TAG_EMP_DELETE_CONTACT = "EMP_DELETE_CONTACT";
        // 任务-会员注册
        public static final String MEMBER_REGISTER = "MEMBER_SYNC_TO_ERP";
        // 任务-活动转发完成
        public static final String ACTIVITY_SHARE_RECORD = "ACTIVITY_SHARE_RECORD";
        // 专属顾问绑定tag
        public static final String EMP_MEMBER_RELATION_BIND = "EMP_MEMBER_RELATION_BIND";
        // 会员生日礼包发送 tag
        public static final String BIRTHDAYGIFT_TAG = "BIRTHDAYGIFT_TAG";
        // 修改外部联系人备注
        public static final String TAG_EMP_EDIT_CONTACT = "EMP_EDIT_CONTACT";
        // 订单状态变更
        public static final String TAG_ORDER_STATUS = "ORDER_STATUS";

        // 群聊消息
        public static final String TAG_GROUP_CHAT_EDIT = "GROUP_CHAT_EDIT";
        // 标签变更
        public static final String TAG_EMP_TAG_EDIT = "TAG_EMP_EDIT";
    }

    /**
     * task相关字符串
     */
    public static class TaskStr {

        // 员工添加新联系人
        public static final String URL = "%s?task_id=%s";
        //会员名称
        public static final String MEMBER_NAME = "memberName";
        //微信昵称
        public static final String WX_NICKNAME = "wxNickname";

        //拉新业务前缀
        public static final String TASK_PULL_NEW = "pull_new";

        public static final String STAT_PULL_NEW_FIX_REWARD_TYPE = "pull_new_reward_fix_stat";

        public static final String STAT_PULL_NEW_DYN_REWARD_TYPE = "pull_new_reward_dyn_stat";

        public static final String STAT_PULL_NEW_NO_REWARD_TYPE = "pull_new_reward_no_stat";
    }

    /**
     * taskTitle
     */
    public static class TaskTitle {
        // 员工添加新联系人
        public static final String APPLY_CARD = "您有一条会员办卡提醒";
        // 活动转发
        public static final String ACTIVITY = "您有一条活动分享任务";
        // 员工建档任务
        public static final String CREATE_RECORD = "您有一个会员病情建档任务";
        //健康咨询
        public static final String HEALTH_CONSULTATION = "您有1条销售咨询";
        //回访任务
        public static final String REVIEW = "您有一个会员回访任务";
        // 用药建议
        public static final String MEMBER_HEALTH = "您有一条用药建议发送任务";
        //商品下单提醒
        public static final String EMP_SHARE_REMIND = "商品下单通知";

    }

    public static class TaskName {
        // 员工添加新联系人
        public static final String APPLY_CARD = "会员办卡提醒";
        //会员建档任务
        public static final String CREATE_RECORD = "会员病情建档任务";
        //会员健康咨询任务
        public static final String HEALTH_CONSULTATION = "会员健康咨询任务";
        //会员回访任务
        public static final String REVIEW = "会员回访任务";
        //员工分享下单提醒
        public static final String SHARE_ORDER_REMIND_TITLE = "商品下单通知";
    }

    public static class Task_Wx {
        // 会员昵称
        public static final String NICKNAME = "会员昵称";
        // 会员名称
        public static final String MEMBERNAME = "会员姓名";
        // 会员名称
        public static final String MEMBER_NAME = "会员姓名";
        // 创建时间
        public static final String CREATE_TIME = "创建时间";
        // 活动名称
        public static final String ACTIVITY_NAME = "活动名称";
        // 活动名称
        public static final String COMMODITY_NAME = "购买商品";

        public static final Integer MSG_READ = 1;

    }

    public static class DataBase {
        //公共模板
        public static final String COMON = "COMON";

        //排序字段
        public static final String isDefault = "is_default";

        //排序字段
        public static final String SORT = "sort";

        //公共模板属性前缀
        public static final String PREX_COMON = "comon_";

    }

    public static class Case {
        //病例
        public static final String CASE = "CASE_";

        //回访病例
        public static final String REVIEW_CASE = "REVIEW_CASE_";

        //默认code值-value只做展示
        public static final String DEFAULT_CODE = "0";

        //开关类型
        public static final String option_type_switch = "2";

    }

    public static class OptionType {
        public static final int SELECT = 1;
        public static final int SWITCH = 3;
    }

    public static class DataType {
        // 普通
        public static final String COMMON = "2";
    }

    public static class Task_Page {
        //办卡任务详情页page
        public static final String PAGE_APPLY_CARD = "/task/register/register?tid=%s&source=1&uk=%s";
        //活动转发任务详情页page
        public static final String PAGE_ACTIVITY_URL = "/marketing/activityDetails/activityDetails?id={0}&source=1&uk={1}";
        //建档任务详情页page 不用了
        public static final String PAGE_CREATE_RECORD = "/pages/putCase/putCase";
        //健康咨询任务详情页page
        public static final String PAGE_HEALTH_CONSULT = "/task/consultation/index?tid=%s&source=1&uk=%s";
        //用药建议企业微信端详情页page
        public static final String PAGE_MEMBER_HEALTH = "/task/medication/index?tid=%s&source=1&uk=%s";
        //用药建议微信小程序详情页page
        public static final String PAGE_MINI_MEMBER_HEALTH = "assistant/medicineSuggest?orderId=%s&merCode=%s&empCode=%s&fromChannel=t-assistant-s&source=1&uk=%s";
        //下单提醒page
        public static final String PAGE_EMP_SHARE_ORDER = "/message/order/reminder?tid=%s&source=1&uk=%s";
    }

    public static class DateFormat {
        public static final String CN_DAY = "yyyy/MM/dd";
    }

    public static class ShareCommodity {
        //状态：1-启用；0-删除
        public static final Integer STATUS_ENABLED = 1;

        public static final Integer STATUS_DELETE = 0;
    }

    public static final String MER_CODE_PATTERN = "[a-zA-Z0-9]{1,10}";
    // 脱敏手机号正则
    public static final String MOBILE_DESENSITIZE_PATTERN = "^[1]([3-9])[0-9|*]{9}$";
    // 脱敏身份证号码正则
    public static final String ID_CARD_DESENSITIZE_PATTERN = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)|(^\\d{3}\\*{11}\\d{3}(\\d|X|x)$)";

    /**
     * 提成方案状态：1-未发布；2-已发布；3-停用；4-作废
     */
    public static class CommissionRuleStatus {
        //未发布 草稿状态
        public static final Integer DRAFT = 1;
        //已发布
        public static final Integer ENABLED = 2;
        //停用
        public static final Integer DISABLED = 3;
        //作废
        public static final Integer CANCELLED = 4;

    }

    //失效日期(yyyyMMdd)，默认100年后，避免空值
    public static final int ONE_HUNDRED_YEAR_TO_DAY = 21200620;
    //失效日期(yyyy-MM-dd HH:mm:ss)，默认10年前，避免空值
    public static final String ONE_HUNDRED_YEAR_TO_DATE = "2100-06-20 00:00:00";
    //生效日期(yyyy-MM-dd HH:mm:ss)，默认10年前，避免空值
    public static final String TEN_YEAR_TO_DAY = "2010-06-20 00:00:00";
    
    public static final String YYYY_MM_DD = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static final String STORE_REPORT_FILENAME = "员工提成报表";

    public static final String STORE_REPORT_SHEETNAME = "员工提成报表";

    public static final String STORE_REPORT_FILENAMESUFFIX = "MM月dd日";
    //"商品编码应少于16位")
    public static final String ERP_CODE_REGEX = "[a-zA-Z0-9]{1,16}";
    // message = "提成比例只能是0或正整数"
    public static final String COMMISSIONRAGE_REGEX = "^[+]{0,1}(\\d+)$";
    /**
     * 提成金额只能是整数或者小数点后2位
     */
    public static final String COMMISSION_AMOUNT_REGEX = "|^(((0|[1-9]\\d{0,9})(\\.\\d{1,2}))|(0|[1-9]\\d{0,9}))$";

    public static final String COMMODITY_CODE_ERR = "商品编码不正确，无法匹配现有商品";

    public static final String COMMISSION_RAGE_ERR = "提成比例不符合录入规范";

    public static final String COMMISSION_AMOUNT_ERR = "提成金额不符合录入规范";

    public static final String COMMODITY_CODE_OVER_ERR = "商品编码重复";

    public static final String AMOUNT_RATE_EMPTY_ERR = "该商品没有设置提成比例或者提成金额";

    public static final Integer COMMISSION_TYPE_RATE = 1;

    public static final Integer COMMODITY_STATUS_ENABLED = 1;
    //审核状态
    public static final Integer AUDIT_STATUS_ENABLED = 1;

    public static final String ERPCODE_ERROR_MSG = "商品编码不正确，无法匹配现有商品";


    public static final Integer COMMISSION_IS_UPDATE_TRUE = 1;

    public static final Integer INTEGER_ZERO = 0;

    public static final String COMMODITY_NOT_PIC_MSG = "该商品没有橱窗图";

    public static final String COMMODITY_DRUG_TYPE_TWO_MSG = "该商品为处方药，不允许上传";

    public static final String COMMODITY_TYPE_ASSEMBLE_MSG = "该商品为组合商品，不允许上传";

    public static final Integer COMMODITY_DRUG_TYPE_TWO = 1;

    public static final Integer COMMODITY_TYPE_ASSEMBLE = 2;

    public static final String YDJIA_MINI_CODE_STR = "merCode=%s&empCode=%s&storeId=%s&activityId=%d&type=10&fromChannel=%s";

    public static final String H5_SHARE_PARAM = "?activityId=%d&merCode=%s&empCode=%s&type=10&fromChannel=%s";

    public static final String MINI_COMMODITY_SHARE_PARAM = "?merCode=%s&shareCode=%d&empCode=%s&fromChannel=%s&from=assistant";

    public static final String EMP_COUPON_SHARE_PARAM = "&couponId=%d&activityId=%d&activityType=%d&empCode=%s&fromChannel=%s&from=assistant&activityTemplateCode=%s";
    // 员工推券 小程序跳转到海典健康链接
    public static final String EMP_COUPON_MINI_SHARE_PARAM = "&couponId=%d&activityId=%d&activityType=%d&empCode=%s&fromChannel=%s&from=assistant&openType=share&activityTemplateCode=%s";

    //    // 群日历商品分享 小程序跳转到海典健康链接
//    public static final String EMP_COMMODITY_MINI_SHARE_PARAM = "/goods/detail?merCode=%s&productId=%s&from=assistant&taskId=%s&planId=%s&chatId=%s&messageId=%s&empCode=%s";
//
//    // 群日历DM单分享 小程序跳转到海典健康链接
//    public static final String EMP_DM_MINI_SHARE_PARAM = "/dm/detail?actFrom=home&merCode=%s&dimensionId=%s&from=assistant&taskId=%s&planId=%s&chatId=%s&messageId=%s&empCode=%s";
    // 群日历商品分享 小程序跳转到海典健康链接
    public static final String EMP_COMMODITY_MINI_SHARE_PARAM = "/goods/detail.html?merCode=%s&productId=%s&empCode=%s";

    // 群日历DM单分享 小程序跳转到海典健康链接
    public static final String EMP_DM_MINI_SHARE_PARAM = "/home/dmdetail.html?actFrom=home&merCode=%s&dimensionId=%s&empCode=%s";

    // 群发消息商品分享 小程序跳转到海典健康链接
    public static final String MSG_COMMODITY_PAGE = "/goods/detail.html?merCode=%s&productId=%s&specId=%s";

    // 群发消息DM单分享 小程序跳转到海典健康链接
    public static final String MSG_DM_PAGE = "/home/dmdetail.html?actFrom=home&merCode=%s&dimensionId=%s";


    public static final String GROUP_CHAT_FILENAME = "客户群列表";
    public static final String CUS_OUTFLOW_FILENAME = "流失客户清单";

    public static final String FLOW_REPORT_FILENAME = "助手流量报表";
    public static final String EMP_REPORT_FILENAME = "员工拉新奖励报表";
    public static final String EMP_REPORT_SHEETNAME = "员工拉新奖励报表";
    public static final String EMP_REPORT_FILENAMESUFFIX = "MM月dd日";
    public static final String EMP_LOGIN_DETAIL_REPORT_FILE_NAME = "员工登录详情报表";
    public static final String EMP_LOGIN_DETAIL_REPORT_SHEET_NAME = "员工登录详情报表";

    public static final String COMMON_NAME_NULL = "通用名为空";
    public static final String BAR_CODE_NULL = "条形码为空";
    public static final String ERP_CODE_NULL = "商品编码不正确";
    public static final String COMMODITY_NAME_NULL = "商品名称为空";
    public static final String SHARE_COMMODITY_PAGE = "marketing/storeShare";

    public static final String EXCEL_SUFFIX_XLSX = ".xlsx";
    public static final String EXCEL_SUFFIX_XLS = ".xls";
    public static final int EXCEL_ROW_LIMIT = 600;
    public static final String FILE_IMPORT_ERROR_MSG = "导入数据%s条，成功%s条，失败%s条，错误文件请点击下载";
    public static final String FILE_IMPORT_SUCCESS_MSG = "导入数据%s条，成功%s条，失败%s条";
    public static final String EXPORT_FILE_NAME_MONTH_DAY_SUFFIX = "MM月dd日";

    /**
     * 任务规则状态
     */
    public static class TaskNotice {
        //0-未发送
        public static final Integer NOT_SEND = 0;
        //1-已发送
        public static final Integer SENT = 1;

        //-1-发送失败
        public static final Integer SENT_FAIL = -1;
    }

    public static class SdpUserType {
        //员工
        public static final Integer EMPLOYEE = 1;
        //分销员
        public static final Integer DIDTRIBUTOR = 2;
    }

    public static class YesOrNo {
        // 是
        public static final Integer YES = 1;
        // 否
        public static final Integer NO = 0;
    }

    public static final String EMP_SHARE_REMIND_CONTENT = "您分享的商品被顾客下单了，快去看看吧。";

    /**
     * 指标全局常量
     */
    public static class Metrics {
        // 奖励金额
        public static final String DEVELOP_REWARD_AMOUNT = "DevelopRewardAmount";
        // 会员开卡数
        public static final String MEMBER_APPLY_CARD_COUNT = "MemberApplyCardCount";
        // 企微拉新
        public static final String WORK_WECHAT_CUSTOMER_COUNT = "WorkWeChatCustomerCount";
        // 单门店级别企微拉新
        public static final String WORK_WECHAT_CUSTOMER_COUNT_BY_STORE = "WorkWeChatCustomerCountByStore";
        // 单门店级别奖励金额
        public static final String DEVELOP_REWARD_AMOUNT_BY_STORE = "DevelopRewardAmountByStore";
        // 单门店级别会员开卡总数
        public static final String MEMBER_APPLY_CARD_COUNT_BY_STORE = "MemberApplyCardCountByStore";
        // 单门店级别小程序开卡
        public static final String MEMBER_APPLY_CARD_COUNT_IS_APPLET_BY_STORE = "MemberApplyCardCountIsAppletByStore";
        // 单门店级别H5开卡
        public static final String MEMBER_APPLY_CARD_COUNT_IS_H5_BY_STORE = "MemberApplyCardCountIsH5ByStore";
        // 企业微信会员开卡数
        public static final String MEMBER_APPLY_CARD_COUNT_IS_ENTERPRISE_WECHAT_BY_STORE = "MemberApplyCardCountIsEnterpriseWechatByStore";
        // 新开会员卡数
        public static final String MEMBER_APPLY_CARD_COUNT_IS_NEW_BY_STORE = "MemberApplyCardCountIsNewByStore";
        // 老会员绑卡数
        public static final String MEMBER_APPLY_CARD_COUNT_IS_OLD_BY_STORE = "MemberApplyCardCountIsOldByStore";
        // 好友会员数
        public static final String CUSTOMER_IS_MEMBER_COUNT = "CustomerIsMemberCount";
        // 好友非会员数
        public static final String CUSTOMER_IS_NO_MEMBER_COUNT = "CustomerIsNoMemberCount";
        // 单门店有效会员数量
        public static final String MEMBER_VALID_CARD_COUNT_BY_STORE = "MemberValidCardCountByStore";
    }

    public static class BATCHS {
        // 批处理数量
        public static final Integer PAGE_SIZE = 500;
    }

    /**
     * 导出 - 首页页码
     */
    public static final int EXPORT_FIRST_PAGE = 1;
    /**
     * 导出 - 每页记录数
     */
    public static final int EXPORT_PAGE_SIZE = 200;

    public static final String SPLIT_SYMBOL = ":";

    /**
     * 门店员工活码唯一键前缀
     * STORE_LIVE_CODE:当前ID号
     */
    public static final String STORE_LIVE_CODE = "STORE_LIVE_CODE" + SPLIT_SYMBOL;

    /**
     * 标签方式 0：关闭自动打标签 1：开启自动打签标
     */
    public static final Integer PUT_AUTO_TAGS_Y = 1;

    /**
     * 标签方式 0：关闭自动打标签 1：开启自动打签标
     */
    public static final Integer PUT_AUTO_TAGS_N = 0;

    /**
     * 员工提成、销售
     */
    public static final String EMP_COMMISSION_AND_SALE_URL ="/1.1/clientEmpPerformance/performanceTotal";
    /**
     * 提成报表
     */
    public static final String EMP_COMMISSION_STATIC_URL = "/1.0/clientCommission/getMonthCommission";
    /**
     * 销售报表
     */
    public static final String EMP_ONLINE_SALE_STATIC_URL = "/1.0/clientCommission/getMonthSale";
    /**
     * 门店速报
     */
    public static final String STORE_REPORT_URL = "/1.0/storeReport/performance";

    public static final String RES_TREE_SEPARATE_SYMBOL = "/";
}
