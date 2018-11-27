package org.walkerljl.boss.dao.dataobject.monitor.v1;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * 监控预期结果
 *
 * @author lijunlin
 */
public class MonitorExpectResultDO extends BaseDO {

    private static final long serialVersionUID = 8600553219940060593L;

    /**
     * 监控对象ID
     */
    private Long   monitorObjectId;
    /**
     * 主体
     */
    private String body;

    public MonitorExpectResultDO() {}

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
