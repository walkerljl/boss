/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * 本软件源代码版权归作者所有,未经许可不得任意复制与传播.
 */
/*
 * Copyright (c) 2010-present www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.support.service.impl;

import java.io.Serializable;
import java.util.List;

import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.boss.support.service.BaseService;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * 基础业务接口实现
 *
 * @param <T>
 * @param <KEY>
 * @author lijunlin
 */
public abstract class BaseServiceImpl<KEY extends Serializable, T> implements BaseService<KEY, T> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 获取DAO操作类
     */
    public abstract BaseDAO<KEY, T> getDAO();

    @Override
    public KEY insert(T entity) {
        return getDAO().insert(entity);
    }

    @Override
    public KEY insert(T entity, SysLog sysLog) {
        return getDAO().insert(entity);
    }

    @Override
    public int insert(List<T> entities) {
        return getDAO().insert(entities);
    }

    @Override
    public int insert(List<T> entities, List<SysLog> sysLogs) {
        return getDAO().insert(entities);
    }

    @Override
    public int deleteByKey(KEY key) {
        return getDAO().deleteByKey(key);
    }

    @Override
    public int deleteByKey(KEY key, SysLog sysLog) {
        return getDAO().deleteByKey(key);
    }

    @Override
    public int deleteByKeys(List<KEY> keys) {
        return getDAO().deleteByKeys(keys);
    }

    @Override
    public int deleteByKeys(List<KEY> keys, SysLog sysLog) {
        return getDAO().deleteByKeys(keys);
    }

    @Override
    public int delete(T condition) {
        return getDAO().delete(condition);
    }

    @Override
    public int delete(T condition, SysLog sysLog) {
        return getDAO().delete(condition);
    }

    @Override
    public int updateByKey(T entity, KEY key) {
        return getDAO().updateByKey(entity, key);
    }

    @Override
    public int updateByKey(T entity, KEY key, SysLog sysLog) {
        return getDAO().updateByKey(entity, key);
    }

    @Override
    public int updateByKeys(T entity, List<KEY> keys) {
        return getDAO().updateByKeys(entity, keys);
    }

    @Override
    public int updateByKeys(T entity, List<KEY> keys, SysLog sysLog) {
        return getDAO().updateByKeys(entity, keys);
    }

    @Override
    public int update(T entity, T condition) {
        return getDAO().update(entity, condition);
    }

    @Override
    public int update(T entity, T condition, SysLog sysLog) {
        return getDAO().update(entity, condition);
    }

    @Override
    public T selectByKey(KEY key) {
        return getDAO().selectByKey(key);
    }

    @Override
    public List<T> selectListByKeys(List<KEY> keys) {
        return getDAO().selectListByKeys(keys);
    }

    @Override
    public T selectOne(T condition) {
        return getDAO().selectOne(condition);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectList(T condition) {
        if (!(condition instanceof Paginator)) {
            return null;
        }
        Paginator<T> paginator = (Paginator<T>) condition;
        return getDAO().selectList(condition, paginator.getCurrentPage(), paginator.getPageSize());
    }

    @Override
    public int selectListCount(T condition) {
        return getDAO().selectListCount(condition);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Paginator<T> selectPage(T condition) {
        if (!(condition instanceof Paginator)) {
            return null;
        }
        Paginator<T> paginator = (Paginator<T>) condition;
        paginator.setDataList(selectList(condition));
        if (paginator.isQueryTotalCount()) {
            paginator.setTotalCount(selectListCount(condition));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Paging select response params,current page:%s,page size:%s, total count:%s.",
                    paginator.getCurrentPage(), paginator.getPageSize(), paginator.getTotalCount()));
        }
        return paginator;
    }

    @Override
    public void addSysLogs(SysLog... sysLogs) {
        // TODO Auto-generated method stub
    }
}