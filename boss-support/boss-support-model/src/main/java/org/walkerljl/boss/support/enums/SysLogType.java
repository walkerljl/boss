/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.enums;


/**
 * 日志类型
 *
 * @author lijunlin
 */
public enum SysLogType {

    /**
     * 添加
     */
    ADD(1, "添加"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 查看
     */
    VIEW(3, "查看"),

    /**
     * 启用
     */
    ENABLE(4, "启用"),

    /**
     * 停用
     */
    DISABLE(5, "停用"),

    /**
     * 删除
     */
    DELETE(6, "删除"),

    /**
     * 物理删除
     */
    PHYSICS_DELETE(7, "物理删除");

    /**
     * 类型值
     */
    private int value;
    /**
     * 类型名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param value
     * @param name
     */
    private SysLogType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 获取类型
     *
     * @param value
     * @return
     */
    public static SysLogType getType(int value) {
        if (value == 0) {
            return null;
        }
        for (SysLogType type : SysLogType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    /**
     * 获取类型值
     *
     * @return
     */
    public int getValue() {
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
        return value + "";
    }
}