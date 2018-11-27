package org.walkerljl.boss.dao.dataobject.monitor.v1;

import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * 监控对象
 *
 * @author lijunlin
 */
@Entity("monitor_object")
public class MonitorObjectDO extends JqueryDatatableBaseDO {

    /**
     * 应用ID
     */
    @Column("app_id")
    private Long    appId;
    /**
     * 类型
     */
    @Column("type")
    private Integer type;
    /**
     * 目标、对象
     */
    @Column("target")
    private String  target;
    /**
     * 频率，单位：秒
     */
    @Column("frequency")
    private Integer frequency;

    //
    private String appName;

    public MonitorObjectDO() {}

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
