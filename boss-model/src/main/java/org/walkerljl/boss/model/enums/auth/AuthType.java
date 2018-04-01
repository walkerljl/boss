/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.model.enums.auth;

/**
 * 授权类型
 *
 * @author lijunlin
 */
public enum AuthType {

    /**
     * 角色授权
     */
    ROLE(1, "角色授权"),

    /**
     * 岗位授权
     */
    POST(2, "岗位授权");

    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名称
     */
    private final String name;

    /**
     * 私有构造函数
     *
     * @param value 类型值
     * @param name  类型名称
     */
    private AuthType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 获取类型对象
     *
     * @param value
     * @return
     */
    public static AuthType getType(Integer value) {
        if (value == null || value.intValue() == 0) {
            return null;
        }
        for (AuthType element : AuthType.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element;
            }
        }
        return null;
    }

    /**
     * 获取类型值
     *
     * @return
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 获取类型名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}