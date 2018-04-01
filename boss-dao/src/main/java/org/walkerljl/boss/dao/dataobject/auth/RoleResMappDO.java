/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth;

import java.util.Date;

/**
 * 角色、资源映射
 *
 * @author lijunlin
 */
public class RoleResMappDO extends BaseAuthDO {

    private static final long serialVersionUID = 1L;

    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 资源码Id
     */
    private Long resCodeId;
    /**
     * 授权生效开始时间
     */
    private Date startTime;
    /**
     * 授权生效结束时间
     */
    private Date endTime;

    //扩展属性
    /**
     * 是否使用授权生效周期, 0:未使用,1:使用
     */
    private Integer useEffCycle;

    public RoleResMappDO() {}

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResCodeId() {
        return resCodeId;
    }

    public void setResCodeId(Long resCodeId) {
        this.resCodeId = resCodeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUseEffCycle() {
        return useEffCycle;
    }

    public void setUseEffCycle(Integer useEffCycle) {
        this.useEffCycle = useEffCycle;
    }
}