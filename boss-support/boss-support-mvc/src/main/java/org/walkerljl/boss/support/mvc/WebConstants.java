package org.walkerljl.boss.support.mvc;

/**
 * WebConstants
 *
 * @author xingxun
 */
public class WebConstants {

    public static final long V = System.currentTimeMillis();

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String VELOCITY_DETAULT_LAYOUT = "layout/default";
    public static final String VELOCITY_SCREEN_CONTENT = "screen_content";
    public static final String ERROR_TEMPLATE = "common/error";
    public static final String INDEX_TEMPLATE = "index/index";
    //请求后缀
    public static final String HTML_SUFFIX = ".html";
    public static final String JSON_SUFFIX = ".json";
    public static final String XML_SUFFIX = ".xml";
    public static final String TXT_SUFFIX = ".txt";
    public static final String VM_SUFFIX = ".vm";

    /**
     * 异常标识key(true 表示发生异常)
     */
    public static final String ERR_FLAG_KEY = "err_flag";
    /**
     * 异常消息key
     */
    public static final String ERR_MSG_KEY = "err_msg";
    /**
     * 异常详细信息key
     */
    public static final String ERR_MSGINFO_KEY = "err_msginfo";
    /**
     * 标识ajax请求状态key(success/failure)
     */
    public static final String JSON_REQ_STATE_KEY = "REQ_FLAG";
    /**
     * 标识ajax请求返回的提示信息
     */
    public static final String JSON_REQ_MSG_KEY = "REQ_MSG";
    /**
     * 标识ajax请求返回的用户数据key
     */
    public static final String JSON_REQ_DATA_KEY = "REQ_DATA";
    /**
     * ajax请求成功
     */
    public static final String JSON_REQ_STATE_SUCCESS = "success";
    /**
     * ajax请求失败
     */
    public static final String JSON_REQ_STATE_FAILURE = "failure";

    public static final String STATIC_RES_REQUEST = "STATIC_RES_REQUEST";
    public static final String DES_KEY = "jarvis..@#ljl%walker%";
    /**
     * cookie key
     */
    public static final String COOKIE_KEY = "jarvis.walkerljl.org";
    public static final String SESSION_KEY = "session_active_account";
    public static final String LOGIN_ACCOUNT_COOKIE_KEY = "jarvis";
    public static final String LOGIN_ACCOUNT_SESSION_KEY = "session_active_account";
    public static final String LOGIN_ACCOUNT_REQUEST_KEY = "request_active_account";

    /**
     * Default IO buffer size (16 KB).
     */
    public static int ioBufferSize = 16384;

    /**
     * Default temp file prefix.
     */
    public static String tempFilePrefix = "jarvis-";

}