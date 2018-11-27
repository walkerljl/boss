package org.walkerljl.boss.support.enums;

import org.walkerljl.toolkit.standard.enums.IEnum;

/**
 * 用户类型
 *
 * @author xingxun
 */
public enum UserTypeEnum implements IEnum {

    /**
     * 普通用户
     */
    NORMAL("normal", "普通用户"),

    /**
     * 超级管理员
     */
    ADMIN("admin", "超级管理员"),

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
    UserTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public StatusEnum getEnumObject(String code) {
        for (StatusEnum item : StatusEnum.values()) {
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