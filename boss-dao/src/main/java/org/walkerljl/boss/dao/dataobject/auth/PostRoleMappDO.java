/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth;

import java.util.Date;

/**
 * 岗位、角色映射
 *
 * @author lijunlin
 */
public class PostRoleMappDO extends BaseAuthDO {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位Id
     */
    private Long postId;
    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 授权生效开始时期
     */
    private Date startDate;
    /**
     * 授权生效结束时期
     */
    private Date endDate;

    //扩展属性
    /**
     * 是否使用授权生效周期, 0:未使用,1:使用
     */
    private Integer useEffCycle;

    public PostRoleMappDO() {}

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public Integer getUseEffCycle() {
        return useEffCycle;
    }

    public void setUseEffCycle(Integer useEffCycle) {
        this.useEffCycle = useEffCycle;
    }
}
