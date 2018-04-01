/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth.res;

import org.walkerljl.boss.support.dao.dataobject.BaseDO;

/**
 * AppInfoBindIp
 *
 * @author lijunlin
 */
public class AppInfoBindIpDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 业务系统Id
     */
    private Long appInfoId;
    /**
     * 绑定的Ip
     */
    private String ip;

    public AppInfoBindIpDO() {}

    public Long getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Long appInfoId) {
        this.appInfoId = appInfoId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}