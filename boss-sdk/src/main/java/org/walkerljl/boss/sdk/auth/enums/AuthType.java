package org.walkerljl.boss.sdk.auth.enums;

import org.walkerljl.boss.sdk.IEnum;

/**
 * 权限验证类型
 *
 * @author xingxun
 */
public enum AuthType implements IEnum {

    /**
     * 登录验证
     */
    LOGIN("login", "登录验证"),

    /**
     * 权限码验证
     */
    CODE("code", "权限码验证"),

    ;

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    AuthType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取枚举对象
     *
     * @param code 编码
     * @return
     */
    public static AuthType getByCode(String code) {
        for (AuthType item : AuthType.values()) {
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