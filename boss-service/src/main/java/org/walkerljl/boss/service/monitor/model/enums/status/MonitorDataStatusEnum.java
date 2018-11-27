package org.walkerljl.boss.service.monitor.model.enums.status;

import org.walkerljl.boss.service.monitor.model.enums.status.base.BaseMonitorStatusIEnum;

/**
 * 监控数据状态
 *
 * @author xingxun
 */
public enum MonitorDataStatusEnum implements BaseMonitorStatusIEnum {

    /**
     * 正常
     */
    NORMAL("10", "正常"),

    /**
     * 已删除
     */
    DELETED("20", "已删除"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    MonitorDataStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public BaseMonitorStatusIEnum getEnumObject(String code) {
        for (MonitorDataStatusEnum ele : this.values()) {
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