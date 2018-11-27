package org.walkerljl.boss.service.retry.model.enums;

import org.walkerljl.boss.service.retry.model.enums.base.BaseRetryStatusIEnum;

/**
 * 重试参数状态
 *
 * @author xingxun
 */
public enum RetryParamStatusEnum implements BaseRetryStatusIEnum {

    /**
     * 正常
     */
    NORMAL("10", "正常"),

    /**
     * 已删除
     */
    DELETED("20", "已删除"),;

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
    RetryParamStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public BaseRetryStatusIEnum getEnumObject(String code) {
        for (BaseRetryStatusIEnum ele : this.values()) {
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