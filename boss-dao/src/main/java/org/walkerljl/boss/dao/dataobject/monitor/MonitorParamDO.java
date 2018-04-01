package org.walkerljl.boss.dao.dataobject.monitor;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * 监控参数
 *
 * @author lijunlin
 */
public class MonitorParamDO extends BaseDO {

    private static final long serialVersionUID = 1502958773854498751L;

    /**
     * 监控对象ID
     */
    private Long monitorObjectId;
    /**
     * 主体
     */
    private String body;

    public MonitorParamDO() {}

    public Long getMonitorObjectId() {
        return monitorObjectId;
    }

    public void setMonitorObjectId(Long monitorObjectId) {
        this.monitorObjectId = monitorObjectId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
