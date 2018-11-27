package org.walkerljl.boss.service.retry.model.enums;

import org.walkerljl.boss.service.retry.model.enums.base.BaseRetryIEnum;

/**
 * 重试优先级
 *
 * @author xingxun
 */
public enum RetryPriorityEnum implements BaseRetryIEnum {

    /**
     * 高
     */
    HIGH("1", "高"),

    /**
     * 中
     */
    NORMAL("5", "中"),

    /**
     * 低
     */
    LOW("9", "低"),;

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
    RetryPriorityEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public BaseRetryIEnum getEnumObject(String code) {
        for (BaseRetryIEnum ele : this.values()) {
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