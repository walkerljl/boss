package org.walkerljl.boss.service.monitor.model.alarm.enums;

import org.walkerljl.boss.service.monitor.model.enums.base.BaseMonitorIEnum;

/**
 * 预警渠道
 *
 * @author xingxun
 */
public enum AlarmChannelEnum implements BaseMonitorIEnum {

    /**
     * 日志预警渠道
     */
    LOG("log", "日志预警渠道"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    AlarmChannelEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public BaseMonitorIEnum getEnumObject(String code) {
        for (AlarmChannelEnum ele : this.values()) {
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