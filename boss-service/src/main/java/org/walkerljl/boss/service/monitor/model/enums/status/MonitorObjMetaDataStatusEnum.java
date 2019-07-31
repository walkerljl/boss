package org.walkerljl.boss.service.monitor.model.enums.status;

import org.walkerljl.boss.service.monitor.model.enums.status.base.BaseMonitorStatusIEnum;

/**
 * 监控对象原始数据状态
 *
 * @author xingxun
 */
public enum MonitorObjMetaDataStatusEnum implements BaseMonitorStatusIEnum {

    /**
     * 正常
     */
    NORMAL("10", "正常"),

    /**
     * 已删除
     */
    DELETED("20", "已删除"),

    /**
     * 暂停预警
     */
    PAUSE_ALARM("30", "暂停预警"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    MonitorObjMetaDataStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public BaseMonitorStatusIEnum getEnumObject(String code) {
        for (MonitorObjMetaDataStatusEnum ele : this.values()) {
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