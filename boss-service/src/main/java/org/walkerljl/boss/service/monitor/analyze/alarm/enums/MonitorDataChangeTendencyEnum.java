package org.walkerljl.boss.service.monitor.analyze.alarm.enums;

import org.walkerljl.boss.service.monitor.model.enums.base.BaseMonitorIEnum;

/**
 * 监控数据变化趋势
 *
 * @author xingxun
 */
public enum MonitorDataChangeTendencyEnum implements BaseMonitorIEnum {

    /**
     * 下降
     */
    DECLINE("decline", "下降"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    MonitorDataChangeTendencyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public BaseMonitorIEnum getEnumObject(String code) {
        for (BaseMonitorIEnum ele : this.values()) {
            if (ele.getCode().equalsIgnoreCase(code)) {
                return ele;
            }
        }
        return null;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}