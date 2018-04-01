package org.walkerljl.boss.sdk.auth.enums;

import org.walkerljl.boss.sdk.IEnum;

/**
 * 鉴权条件
 *
 * @author xingxun
 */
public enum AuthCondition implements IEnum {

    /** 与的关系  */
    AND("and", "与的关系"),

    /** 或的关系  */
    OR("or", "或的关系"),

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
    AuthCondition(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过编码获取枚举对象
     *
     * @param code 编码
     * @return
     */
    public static AuthCondition getByCode(String code) {
        for (AuthCondition item : AuthCondition.values()) {
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