package org.walkerljl.boss.service.monitor.model.alarm.enums;

import org.walkerljl.boss.service.monitor.model.enums.base.BaseMonitorIEnum;

/**
 * 预警规则类型
 *
 * @author xingxun
 */
public enum AlarmRuleTypeEnum implements BaseMonitorIEnum {

    /**
     * 多日连续变动达到一定百分比
     */
    MULTI_DAYS_CHANGE_EXCEED_PERCENT("1000", "多日连续变动达到一定百分比"),

    /**
     * 单日值达到最近N天峰值
     */
    SINGLE_DAY_MAX_IN_N_DAYS("1001", "单日值达到最近N天峰值"),

    /**
     * 达成当月KPI目标
     */
    REACH_CURRENT_MONTH_GOAL("1002", "达成当月KPI目标"),;

    /**
     * 构造函数
     *
     * @param code 编码
     * @param description 描述
     */
    AlarmRuleTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /** 编码*/
    private String         code;
    /** 描述*/
    private String         description;

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