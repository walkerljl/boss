/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.dao.dataobject.auth;

/**
 * 权限部门
 *
 * @author lijunlin
 */
public class PostDO extends BaseAuthDO {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 简称
     */
    private String simpleName;

    public PostDO() {}

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }
}