/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth;

import java.util.Date;

/**
 * 授权关系实体
 *
 * @author lijunlin
 */
public class AuthorizationDO extends BaseAuthDO {

    private static final long serialVersionUID = 1L;

    /**
     * 授权对象类型
     */
    private Integer objectType;
    /**
     * 授权对象Id
     */
    private String objectId;
    /**
     * 授权类型
     */
    private Integer authType;
    /**
     * 授权Id
     */
    private Long authId;
    /**
     * 是否使用授权生效周期, 0:未使用,1:使用
     */
    private Integer useEffCycle;
    /**
     * 授权生效开始时期
     */
    private Date startDate;
    /**
     * 授权生效结束时期
     */
    private Date endDate;

    public AuthorizationDO() {}

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Integer getUseEffCycle() {
        return useEffCycle;
    }

    public void setUseEffCycle(Integer useEffCycle) {
        this.useEffCycle = useEffCycle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}