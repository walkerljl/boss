package org.walkerljl.boss.service.monitor.model.alarm.enums;

import org.walkerljl.boss.service.monitor.model.enums.base.BaseMonitorIEnum;

/**
 * 预警等级
 *
 * @author xingxun
 */
public enum AlarmLevelEnum implements BaseMonitorIEnum {

    /**
     * 绿，一般用于提示信息
     */
    GREEN("10", "绿"),

    /**
     * 黄
     */
    YELLOW("20", "黄"),

    /**
     * 橙
     */
    ORGANE("30", "橙"),

    /**
     * 红
     */
    RED("40", "红"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    AlarmLevelEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String code;
    /** 描述*/
    private String description;

    @Override
    public BaseMonitorIEnum getEnumObject(String code) {
        for (BaseMonitorIEnum ele : this.values()) {
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