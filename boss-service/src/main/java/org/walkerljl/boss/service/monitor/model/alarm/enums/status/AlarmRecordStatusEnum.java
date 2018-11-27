package org.walkerljl.boss.service.monitor.model.alarm.enums.status;

import org.walkerljl.boss.service.monitor.model.enums.status.base.BaseMonitorStatusIEnum;

/**
 * 预警记录状态
 *
 * @author xingxun
 */
public enum AlarmRecordStatusEnum implements BaseMonitorStatusIEnum {

    /**
     * 已删除
     */
    DELETED("20", "已删除"),

    /**
     * 未预警
     */
    UNALARM("30", "未预警"),

    /**
     * 已预警
     */
    ALARMED("31", "已预警"),

    ;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    AlarmRecordStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public BaseMonitorStatusIEnum getEnumObject(String code) {
        for (BaseMonitorStatusIEnum ele : this.values()) {
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