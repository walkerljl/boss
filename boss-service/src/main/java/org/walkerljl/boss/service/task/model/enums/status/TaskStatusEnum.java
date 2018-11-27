package org.walkerljl.boss.service.task.model.enums.status;

import org.walkerljl.boss.service.task.model.enums.base.BaseTaskStatusIEnum;

/**
 * 任务状态
 *
 * @author xingxun
 */
public enum TaskStatusEnum implements BaseTaskStatusIEnum {

    /**
     * 已删除
     */
    DELETED("20", "已删除"),

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
    FAILURE("33", "处理失败"),

    /**
     * 初始化
     */
    INITIALIZE("34", "初始化"),
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
    TaskStatusEnum(String code, String description) {
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