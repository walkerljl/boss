package org.walkerljl.boss.support.mvc.auth;

import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 鉴权错误码
 *
 * @author xingxun
 */
public enum AuthenticationErrorCode implements ErrorCode {

    /**
     * 没有登录
     */
    NOT_LOGGED_IN("permission-no-login", "没有登录"),

    /**
     * 无操作权限
     */
    PERMISSION_DENIED("permission-denied", "无操作权限"),;

    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;

    /**
     * 构造函数
     *
     * @param code        编码
     * @param description 描述
     */
    AuthenticationErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public AuthenticationErrorCode getEnumObject(String code) {
        for (AuthenticationErrorCode item : AuthenticationErrorCode.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}