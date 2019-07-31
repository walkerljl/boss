package org.walkerljl.boss.common.model.core.enums;import org.walkerljl.toolkit.standard.enums.IEnum;

/**
 * 业务布尔值枚举
 *
 * @author xingxun
 */
public enum BizBooleanIEnum implements IEnum {

    /**
     * true
     */
    TRUE("1", "true"),

    /**
     * false
     */
    FALSE("2", "false"),

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
    BizBooleanIEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public IEnum getEnumObject(String code) {
        for (IEnum ele : values()) {
            if (ele.getCode().equalsIgnoreCase(code)) {
                return ele;
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