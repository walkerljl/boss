package org.walkerljl.boss.support.service;


import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

import java.io.Serializable;
import java.util.List;

/**
 * 基础业务接口
 *
 * @param <KEY>
 * @param <T>
 * @author lijunlin
 */
public interface BaseService<KEY extends Serializable, T> {

    /**
     * 添加对象
     *
     * @param entity
     * @return 返回主键
     */
    KEY insert(T entity);

    /**
     * 添加对象
     *
     * @param entity
     * @param sysLog
     * @return 返回主键
     */
    KEY insert(T entity, SysLog sysLog);

    /**
     * 批量添加对象
     *
     * @param entities
     * @return
     */
    int insert(List<T> entities);

    /**
     * 批量添加对象
     *
     * @param entities
     * @param sysLogs
     * @return
     */
    int insert(List<T> entities, List<SysLog> sysLogs);

    /**
     * 根据主键删除对象
     *
     * @param key
     * @return
     */
    int deleteByKey(KEY key);

    /**
     * 根据主键删除对象
     *
     * @param key
     * @param sysLog
     * @return
     */
    int deleteByKey(KEY key, SysLog sysLog);

    /**
     * 根据主键列表批量删除对象
     *
     * @param keys
     * @return
     */
    int deleteByKeys(List<KEY> keys);

    /**
     * 根据主键列表批量删除对象
     *
     * @param keys
     * @param sysLog
     * @return
     */
    int deleteByKeys(List<KEY> keys, SysLog sysLog);

    /**
     * 删除对象,只要不为NULL与空则为条件
     *
     * @param condition
     * @return
     */
    int delete(T condition);

    /**
     * 删除对象,只要不为NULL与空则为条件
     *
     * @param condition
     * @param sysLog
     * @return
     */
    int delete(T condition, SysLog sysLog);

    /**
     * 根据主键更新对象
     *
     * @param entity
     * @param key
     * @return
     */
    int updateByKey(T entity, KEY key);

    /**
     * 根据主键更新对象
     *
     * @param entity
     * @param key
     * @param sysLog
     * @return
     */
    int updateByKey(T entity, KEY key, SysLog sysLog);

    /**
     * 根据主键列表更新对象
     *
     * @param entity
     * @param keys
     * @return
     */
    int updateByKeys(T entity, List<KEY> keys);

    /**
     * 根据主键列表更新对象
     *
     * @param entity
     * @param keys
     * @param sysLog
     * @return
     */
    int updateByKeys(T entity, List<KEY> keys, SysLog sysLog);

    /**
     * 更新对象
     *
     * @param entity    待更新对象
     * @param condition 更新条件,不为空字段为条件
     * @return
     */
    int update(T entity, T condition);

    /**
     * 更新对象
     *
     * @param entity    待更新对象
     * @param condition 更新条件,不为空字段为条件
     * @param sysLog
     * @return
     */
    int update(T entity, T condition, SysLog sysLog);

    /**
     * 根据主键获取对象
     *
     * @param key
     * @return
     */
    T selectByKey(KEY key);

    /**
     * 根据主键列表获取对象
     *
     * @param keys
     * @return
     */
    List<T> selectListByKeys(List<KEY> keys);

    /**
     * 查询单个对象
     *
     * @param condition
     * @return
     */
    T selectOne(T condition);

    /**
     * 查询对象,只要不为NULL与空则为条件
     *
     * @param condition
     * @return
     */
    List<T> selectList(T condition);

    /**
     * 查询对象总数,只要不为NULL与空则为条件
     *
     * @param condition
     * @return
     */
    int selectListCount(T condition);

    /**
     * 分页查询
     *
     * @param condition
     * @return
     */
    Paginator<T> selectPage(T condition);

    /**
     * 添加系统日志
     *
     * @param sysLogs
     */
    void addSysLogs(SysLog... sysLogs);
}