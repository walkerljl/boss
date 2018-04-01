package org.walkerljl.boss.support.dao.daointerface;

import java.io.Serializable;
import java.util.List;

/**
 * 默认的数据访问层基础接口
 *
 * @author lijunlin
 */
public interface BaseDAO<KEY extends Serializable, T> {

    /**
     * 添加对象
     *
     * @param entity
     * @return 返回主键
     */
    KEY insert(T entity);

    /**
     * 批量添加对象
     *
     * @param entities
     * @return
     */
    int insert(List<T> entities);

    /**
     * 根据主键删除对象
     *
     * @param key
     * @return
     */
    int deleteByKey(KEY key);

    /**
     * 根据主键列表批量删除对象
     *
     * @param keys
     * @return
     */
    int deleteByKeys(List<KEY> keys);

    /**
     * 删除对象,只要不为NULL与空则为条件
     *
     * @param condition
     * @return
     */
    int delete(T condition);

    /**
     * 根据主键更新对象
     *
     * @param entity
     * @param key
     * @return
     */
    int updateByKey(T entity, KEY key);

    /**
     * 根据主键列表更新对象
     *
     * @param entity
     * @param keys
     * @return
     */
    int updateByKeys(T entity, List<KEY> keys);

    /**
     * 更新对象
     *
     * @param entity    待更新对象
     * @param condition 更新条件,不为空字段为条件
     * @return
     */
    int update(T entity, T condition);

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
     * @param currentPage 当前页码
     * @param pageSize    每页大小
     * @return
     */
    List<T> selectList(T condition, int currentPage, int pageSize);

    /**
     * 查询对象总数,只要不为NULL与空则为条件
     *
     * @param condition
     * @return
     */
    int selectListCount(T condition);
}