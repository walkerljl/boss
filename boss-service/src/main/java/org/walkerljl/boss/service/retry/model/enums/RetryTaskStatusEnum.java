package org.walkerljl.boss.service.retry.model.enums;

import org.walkerljl.boss.service.retry.model.enums.base.BaseRetryStatusIEnum;

/**
 * 重试任务状态
 *
 * @author xingxun
 */
public enum RetryTaskStatusEnum implements BaseRetryStatusIEnum {

    /**
     * 已删除
     */
    DELETED("10", "已删除"),

    /**
     * 未处理
     */
    UNPROCESS("30", "未处理"),

    /**
     * 处理中
     */
    PROCESSING("31", "处理中"),

    /**
     * 已处理
     */
    PROCESSED("32", "已处理"),

    /**
     * 处理失败
     */
    FAILURE("33", "处理失败"),;

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
    RetryTaskStatusEnum(String code, String description) {
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