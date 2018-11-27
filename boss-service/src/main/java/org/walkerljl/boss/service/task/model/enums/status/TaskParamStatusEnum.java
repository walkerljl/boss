package org.walkerljl.boss.service.task.model.enums.status;

import org.walkerljl.boss.service.task.model.enums.base.BaseTaskStatusIEnum;

/**
 * 任务参数状态
 *
 * @author xingxun
 */
public enum TaskParamStatusEnum implements BaseTaskStatusIEnum {

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
    TaskParamStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public BaseTaskStatusIEnum getEnumObject(String code) {
        for (BaseTaskStatusIEnum ele : this.values()) {
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