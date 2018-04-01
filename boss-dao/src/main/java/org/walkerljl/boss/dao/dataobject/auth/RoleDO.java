/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth;

import java.util.Date;

/**
 * 角色
 *
 * @author lijunlin
 */
public class RoleDO extends BaseAuthDO {

    private static final long serialVersionUID = 1L;

    /**
     * 授权生效开始时间
     */
    private Date startTime;
    /**
     * 授权生效结束时间
     */
    private Date endTime;

    /**
     * 默认构造函数
     */
    public RoleDO() {}

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
}
