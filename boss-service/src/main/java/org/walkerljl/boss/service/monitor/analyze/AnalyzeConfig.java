package org.walkerljl.boss.service.monitor.analyze;

import org.walkerljl.boss.service.monitor.model.base.BaseMonitorModel;

/**
 * 分析配置
 *
 * @author xingxun
 */
public class AnalyzeConfig extends BaseMonitorModel {

    private static final long serialVersionUID = -7879247108144590163L;

    /** 操作者ID*/
    private String operatorId = "system";

    /**
     * Getter method for property <tt>operatorId</tt>.
     *
     * @return property value of operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}