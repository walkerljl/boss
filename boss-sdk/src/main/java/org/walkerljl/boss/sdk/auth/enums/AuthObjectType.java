/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.sdk.auth.enums;

import org.walkerljl.boss.sdk.IEnum;

/**
 * 授权对象类型
 *
 * @author xingxun
 */
public enum AuthObjectType implements IEnum {

    /**
     * 用户
     */
    USER("user", "用户"),

    /**
     * 应用/接入的业务系统
     */
    APP("app", "应用"),

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
    AuthObjectType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取枚举对象
     *
     * @param code 编码
     * @return
     */
    public static AuthObjectType getByCode(String code) {
        for (AuthObjectType item : AuthObjectType.values()) {
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