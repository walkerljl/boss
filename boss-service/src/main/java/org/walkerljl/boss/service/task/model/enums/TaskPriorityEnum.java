package org.walkerljl.boss.service.task.model.enums;

import org.walkerljl.boss.service.task.model.enums.base.BaseTaskIEnum;

/**
 * 任务优先级
 *
 * @author xingxun
 */
public enum TaskPriorityEnum implements BaseTaskIEnum {

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
    TaskPriorityEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public BaseTaskIEnum getEnumObject(String code) {
        for (BaseTaskIEnum ele : this.values()) {
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