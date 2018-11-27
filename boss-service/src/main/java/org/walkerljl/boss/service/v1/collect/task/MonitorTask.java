package org.walkerljl.boss.service.v1.collect.task;

import java.util.Date;

import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.support.BaseEntity;

/**
 * 监控任务
 *
 * @author lijunlin
 */
public class MonitorTask extends BaseEntity {

    /**
     * 应用ID
     */
    private Long              appId;
    /**
     * 监控对象ID
     */
    private Long              monitorObjectId;
    /**
     * 类型
     */
    private MonitorObjectType type;
    /**
     * 目标、对象
     */
    private String            target;
    /**
     * 创建时间
     */
    private Date              created;

    public MonitorTask() {
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getMonitorObjectId() {
        return monitorObjectId;
    }

    public void setMonitorObjectId(Long monitorObjectId) {
        this.monitorObjectId = monitorObjectId;
    }

    public MonitorObjectType getType() {
        return type;
    }

    public void setType(MonitorObjectType type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}