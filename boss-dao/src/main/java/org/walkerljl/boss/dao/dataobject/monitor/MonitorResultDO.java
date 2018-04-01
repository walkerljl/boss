package org.walkerljl.boss.dao.dataobject.monitor;

import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * 监控结果
 *
 * @author lijunlin
 */
@Entity("monitor_result")
public class MonitorResultDO extends JqueryDatatableBaseDO {

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAILURE = 0;

    /**
     * 应用ID
     */
    @Column("app_id")
    private Long appId;
    /**
     * 监控对象ID
     */
    @Column("monitor_object_id")
    private Long monitorObjectId;
    /**
     * 监控类型
     */
    @Column("monitor_type")
    private Integer monitorType;
    /**
     * 是否成功标志
     */
    @Column("code")
    private Integer code = CODE_SUCCESS;
    /**
     * 内容
     */
    @Column("content")
    private String content;

    //
    private String appName;
    private String monitorObjectName;

    public MonitorResultDO() {}

    public String getMonitorTypeName() {
        MonitorObjectType monitorObjectType = MonitorObjectType.getType(monitorType == null ? 0 : monitorType.intValue());
        return monitorObjectType == null ? "" : monitorObjectType.getName();
    }

    public String getCodeName() {
        return code == CODE_SUCCESS ? "成功" : "失败";
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

    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMonitorObjectName() {
        return monitorObjectName;
    }

    public void setMonitorObjectName(String monitorObjectName) {
        this.monitorObjectName = monitorObjectName;
    }
}
