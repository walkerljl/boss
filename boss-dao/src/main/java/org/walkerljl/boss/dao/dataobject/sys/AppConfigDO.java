package org.walkerljl.boss.dao.dataobject.sys;

import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * 应用配置
 *
 * @author lijunlin
 */
@Entity("app_config")
public class AppConfigDO extends JqueryDatatableBaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @Column("app_id")
    private Long appId;
    /**
     * 名称
     */
    @Column("name")
    private String name;
    /**
     * 配置Key
     */
    @Column("key")
    private String key;
    /**
     * 配置Value
     */
    @Column("value")
    private String value;

    //
    private String appName;

    /**
     * 默认构造函数
     */
    public AppConfigDO() {}

    //

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}