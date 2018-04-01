/*
 * Copyright (c) 2010-2015 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.mvc.model.enums;

import org.walkerljl.toolkit.standard.enums.IEnum;

/**
 * 编辑类型
 *
 * @author xingxun
 */
public enum EditTypeEnum implements IEnum {

    /**
     * 新增
     */
    ADD("add", "新增"),

    /**
     * 修改
     */
    EDIT("edit", "修改"),

    /**
     * 详情
     */
    VIEW("view", "详情");

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
    EditTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取类型对象
     *
     * @param code 编码
     * @return
     */
    public static EditTypeEnum getByCode(String code) {

        for (EditTypeEnum item : EditTypeEnum.values()) {
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
