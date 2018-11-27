package org.walkerljl.boss.support.enums;

import org.walkerljl.toolkit.standard.enums.IEnum;

/**
 * StatusEnum
 *
 * @author lijunlin
 */
public enum StatusEnum implements IEnum {

    /**
     * 可用
     */
    ENABLED("1", "可用"),

    /**
     * 不可用
     */
    DISABLED("2", "不可用"),

    /**
     * 删除
     */
    DELETED("3", "删除")

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
    StatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取类型对象
     *
     * @param code 编码
     * @return
     */
    public static StatusEnum getByCode(String code) {

        for (StatusEnum item : StatusEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public StatusEnum getEnumObject(String code) {
        return getByCode(code);
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