/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.identity;

import org.walkerljl.boss.support.dao.dataobject.JqueryDatatableBaseDO;
import org.walkerljl.toolkit.db.api.annotation.Column;
import org.walkerljl.toolkit.db.api.annotation.Entity;

/**
 * 应用
 *
 * @author lijunlin
 */
@Entity("id_app")
public class AppDO extends JqueryDatatableBaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Column("name")
    private String name;
    /**
     * 编码
     */
    @Column("code")
    private String code;
    /**
     * 域名
     */
    @Column("domain")
    private String domain;
    /**
     * 图标
     */
    @Column("icon")
    private String icon;
    /**
     * 管理者/负责人Id
     */
    @Column("manager_id")
    private String managerId;
    /**
     * 管理者/负责人姓名
     */
    @Column("manager_name")
    private String managerName;
    /**
     * 是否使用岗位授权,0:未使用,1:使用
     */
    @Column("use_post_auth")
    private Integer usePostAuth;
    /**
     * token
     */
    @Column("token")
    private String token;

    /**
     * 默认构造函数
     */
    public AppDO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getUsePostAuth() {
        return usePostAuth;
    }

    public void setUsePostAuth(Integer usePostAuth) {
        this.usePostAuth = usePostAuth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}