package com.fqyc.demo.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 异常编码常量，异常编码的规则为：${spring.application.name}-${exceptionCode}
 *
 * @author panyi
 * @date 2020-03-24 14:26
 * @since 1.0
 */
public class ExceptionCodeConstants {
    private ExceptionCodeConstants() {
    }

    public static final String ARGUMENT_NULL = "10001";
    public static final String ARGUMENT_ERR = "10002";
    public static final String TOKEN_EXPIRE = "10003";
    public static final String CASE_NOT_EXIST = "10004";
    public static final String EMPCUSTOMER_NOT_EXIST = "10006";
    public static final String EMPINVENTORY_MANX_ERR = "10007";
    public static final String MERCODE_NULL = "10008";
    public static final String INTERNEL_ERR = "10999";
    public static final String BIZ_ERR_CODE = "19999";

    public static final String NOT_FILE_KEY = "10018";
    public static final String NOT_STORE_MSG = "10020";
    public static final String HAVE_ADD_MSG = "10021";
    public static final String FAILD_TO_GET_MYCLIENT = "10022";
    //lck add
    public static final String ACTIVITY_PAGE_FAIL = "10030";
    public static final String ACTIVITY_DETAIL_FAIL = "10031";
    public static final String SHARE_ACTIVITY_FAIL = "10032";
    public static final String EMP_PUSH_COUPON_FAIL = "10033";
    public static final String EMP_PUSH_COUPON_QRCODE_FAIL = "10034";
    public static final String APP_ERROR_REPORT_FAIL = "10035";
    public static final String CASE_PAGE_FAIL = "10036";
    public static final String CASE_INFO_FAIL = "10037";
    public static final String CASE_DETAIL_FAIL = "10038";
    public static final String REVIEW_CASE_DETAIL_FAIL = "10039";
    public static final String EMP_COMMISSION_HOME_FAIL = "10040";
    public static final String EMP_SALE_MONTH_FAIL = "10041";
    public static final String EMP_COMMISSION_MONTH_FAIL = "10042";
    public static final String ALL_EMP_SALE_RANK_FAIL = "10043";
    public static final String EMP_SALE_RANK_FAIL = "10044";
    public static final String All_STORE_SALE_RANK_FAIL = "10045";
    public static final String STORE_SALE_RANK_FAIL = "10046";
    public static final String EMP_COMMISSION_DAY_FAIL = "10047";
    public static final String EMP_PERFORMANCE_TOTAL_FAIL = "10048";
    public static final String EMP_PERFORMANCE_PAGE_FAIL = "10049";
    public static final String EMP_ASSET_INFO_FAIL = "10050";
    public static final String EMP_REMIND_PAGE_FAIL = "10051";
    public static final String EMP_REMIND_DETAIL_FAIL = "10052";
    public static final String UPDATE_REMIND_STATE_FAIL = "10053";
    public static final String EMP_NOT_FINISHED_COUNT_FAIL = "10054";
    public static final String EMP_TASK_HOME_PAGE_FAIL = "10055";
    public static final String EMP_TASK_RULE_DETAIL_FAIL = "10056";
    public static final String EMP_HEALTH_TASK_DETAIL_FAIL = "10057";
    public static final String EMP_HEALTH_TASK_FINISH_FAIL = "10058";
    public static final String EMP_TASK_HOME_FAIL = "10059";
    public static final String EMP_TASK_Page_FAIL = "10060";
    public static final String MY_FAVORITE_MATERIAL_PAGE_FAIL = "10061";
    public static final String MATERIAL_PAGE_FAIL = "10062";
    public static final String FAVOR_MATERIAL_FAIL = "10063";
    public static final String CANCEL_FAVOR_MATERIAL_FAIL = "10064";
    public static final String FORWARD_MATERIAL_FAIL = "10065";
    public static final String GET_MYCLIENT_PAGE_FAIL = "10066";
    public static final String GET_MYCLIENT_DETAIL_FAIL = "10067";
    public static final String RECOMMEND_COMMODITY_PAGE_FAIL = "10068";
    public static final String SUGGEST_PAGE_FAIL = "10069";
    public static final String SUGGEST_DETAIL_FAIL = "10070";
    public static final String ADD_SUGGEST_TO_READ_FAIL = "10071";
    public static final String UPLOAD_IMAGE_ERROR_FAIL = "10072";
    public static final String SHARE_COMMODITY_FAIL = "10073";
    public static final String QUERY_COMMODITY_FAIL = "10322";
    public static final String GET_SHARE_CODE_FAIL = "10074";
    public static final String EMP_INFO_FAIL = "10078";
    public static final String EMP_UPDATE_FAIL = "10079";
    public static final String SYNC_EMP_INFO_FAIL = "10080";
    public static final String EMP_CUSTOMER_IS_RELATION_EXIST_FAIL = "10081";
    public static final String EMP_CUSTOMER_IS_RELATION_EXIST_BATCH_FAIL = "10082";
    public static final String FETCH_CONTACT_ME_CONFIGS_FAIL = "10083";
    public static final String FETCH_CONTACT_ME_CONFIGS_BATCH_FAIL = "10084";
    public static final String ADD_INVENTORY_COMMODITY_FAIL = "10085";
    public static final String DELETE_INVENTORY_COMMODITY_FAIL = "10086";
    public static final String INVENTORY_COMMODITY_LIST_FAIL = "10087";
    public static final String INVENTORY_COMMODITY_COUNT_FAIL = "10088";
    public static final String USER_NO_STORE = "10089";
    public static final String EXCEL_FILE_ERROR = "10090";
    public static final String QUERY_STORE_FAIL = "10091";
    public static final String EXCEL_FORMAT_ERROR = "10092";
    public static final String EXCEL_SIZE_ERROR = "10093";
    public static final String EXCEL_TEMPLATE_ERROR = "10094";

    // zl add
    public static final String SMS_SEND_FAIL = "10101";
    public static final String SMS_VERIFY_FAIL = "10102";
    public static final String SEARCH_HISTORY_LIST_FAIL = "10103";
    public static final String NEW_SEARCH_HISTORY_LIST_FAIL = "10104";
    public static final String SEARCH_HISTORY_CLEAR_FAIL = "10105";
    public static final String NEW_SEARCH_HISTORY_CLEAR_FAIL = "10106";
    public static final String RECOMMEND_LIST_FAIL = "10107";
    public static final String RECOMMEND_EDIT_FAIL = "10108";
    public static final String OSS_FILE_DOWNLOAD_FAIL = "10109";
    public static final String ADD_CONTACT_WAY_FAIL = "10110";
    public static final String QUERY_DIMENSION_TREE_FAIL = "10111";
    public static final String SEARCH_COMMODITY_FAIL = "10112";
    public static final String ADD_RETURN_VISIT_FAIL = "10113";
    public static final String MARK_AS_NOT_TRACK_FAIL = "10114";
    public static final String MODIFY_HEALTH_RECORD_FAIL = "10115";
    public static final String ADD_HEALTH_RECORD_FAIL = "10116";
    public static final String DETAIL_HEALTH_RECORD_FAIL = "10117";
    public static final String LIST_HEALTH_RECORD_FAIL = "10118";
    public static final String DETAIL_MEMBER_BY_CARD_FAIL = "10119";
    public static final String MEMBER_REPORT_FAIL = "10120";
    public static final String MEMBER_STATICS_FAIL = "10121";
    public static final String MEMBER_CHECK_CONTACT_FAIL = "10122";
    public static final String MEMBER_COUPON_CODE_FAIL = "10123";
    public static final String MEMBER_COUPON_LIST_FAIL = "10124";
    public static final String MEMBER_CONSUME_LIST_FAIL = "10125";
    public static final String MEMBER_INFO_FAIL = "10126";
    public static final String MEMBER_LIST_FAIL = "10127";
    public static final String MEMBER_INDEX_FAIL = "10128";
    public static final String MEMBER_REGISTER_FAIL = "10129";
    public static final String BASE_RECORD_ADD_FAIL = "10130";
    public static final String BASE_RECORD_LIST_FAIL = "10131";
    public static final String BASE_RECORD_DELETE_FAIL = "10132";
    public static final String MOBILE_LOGIN_FAIL = "10133";
    public static final String ADMIN_LOGIN_FAIL = "10134";
    public static final String LABEL_LIST_FAIL = "10135";
    public static final String EMPLOYEE_TASK_DETAIL_FAIL = "10136";
    public static final String EMPLOYEE_TASK_HEALTH_CONSULT_ADD_FAIL = "10137";
    public static final String EMPLOYEE_TASK_PULL_NEW_FINISH_FAIL = "10138";
    public static final String QUERY_MY_MERCHANT_FAIL = "10139";
    public static final String DISTRIBUTION_INFO_FAIL = "10140";
    public static final String DISTRIBUTION_INVITE_LIST_FAIL = "10141";
    public static final String DISTRIBUTION_COMMITION_LIST_FAIL = "10142";

    public static final String MEMBER_HEALTH_DETAIL_FAIL = "10143";
    public static final String MEMBER_HEALTH_FINISH_FAIL = "10144";

    public static final String GET_MINI_SHARE_PARAM_FAIL = "10170";

    public static final String GET_EMP_INFO_FAIL = "10175";
    public static final String EMP_ORDER_REMIND_FAIL = "10176";


    public static final String HEALTH_QUERY_FAIL = "10150";
    public static final String HEALTH_COMMODITY_LIST_FAIL = "10151";
    public static final String TEMPLATE_DOWNLOAD_FAIL = "10152";
    public static final String IMPORT_COMMODITY_FAIL = "10153";
    public static final String SEARCH_MERCHANT_COMMODITY_FAIL = "10154";
    public static final String QUERY_TYPE_DIMENSION_TREE_FAIL = "10155";
    public static final String HEALTH_DELETE_COMMODITY_FAIL = "10156";
    public static final String HEALTH_CONFIG_INFO_FAIL = "10157";
    public static final String HEALTH_CONFIG_UPDATE_FAIL = "10158";
    public static final String HEALTH_ADD_COMMODITY_FAIL = "10159";


    public static final String GROUP_CHAT_LIST_SYN_FAIL = "10185";
    public static final String GROUP_CHAT_SYN_FAIL = "10186";


    // QYP add
    public static final String STORE_OR_EMP_FAIL = "10183";



    public static final String GET_APP_ID_FAIL = "201202001";

    public static final String EDIT_MEM_TAGS_FAIL = "10180";
    public static final String SHOPPING_CART_LIST_FAIL = "10181";
    public static final String MEM_RECENTLY_READ_FAIL = "10182";
    public static final String TODAY_GROUP_SEND_LIST_FAIL = "10200";
    public static final String FINISH_GROUP_SEND_FAIL = "10201";
    public static final String GET_PLAN_IMAGE_MEDIAID_FAIL = "10202";

    public static final String GET_STORE_CODE_FAIL = "10200";

    public static final String QUERY_BATCH_NUM_FAIL = "10220";
    public static final String INVENTORY_QUERY_FAIL = "10221";

    public static final String GET_MEMBER_ID_CARD_ERROR = "10301";
    public static final String SEARCH_COMMODITY_NAME_FAIL = "10302";


    public static final String CREATE_PLAN_TASK_FAIL = "10303";
    public static final String GET_PLAN_TASK_DETAIL_FAIL = "10304";
    public static final String UPDATE_PLAN_TASK_INFO_FAIL = "10305";
    public static final String IMPORT_STORE_PLAN_TASK_FAIL = "10306";
    public static final String UPDATE_PLAN_TASK_STATUS_FAIL = "10307";
    public static final String GET_PLAN_TASK_DATA_FAIL = "10308";
    public static final String GET_PLAN_TASK_LIST_FAIL = "10309";

    public static final String GET_PRODUCT_SALE_TASK_DATA_FAIL = "10310";
    public static final String GET_STORES_INFO_BY_TASK_ID_FAIL = "10311";

    public static final String MERCHANT_REPORT_QUERY_STATISTICS_FAIL = "10320";
    public static final String MERCHANT_REPORT_QUERY_LOGIN_STATISTICS_FAIL = "10321";


    public static final List<String> IGNORE_CODES = Arrays.asList("30007");
}
