package org.walkerljl.boss.support.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.walkerljl.boss.support.dao.daointerface.BaseDAO;
import org.walkerljl.toolkit.db.orm.session.SqlSession;
import org.walkerljl.toolkit.lang.CollectionUtils;
import org.walkerljl.toolkit.lang.ListUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * 默认的数据访问层基础接口实现
 *
 * @author lijunlin
 */
public class BaseDAOImpl<KEY extends Serializable, T> extends MyBatisSupport implements BaseDAO<KEY, T> {

    /**
     * 批量插入大小
     */
    private static final int      BATCH_INSERT_SIZE = 500;
    protected final      Logger   LOGGER            = LoggerFactory.getLogger(getClass());
    @SuppressWarnings("unchecked")
    private              Class<T> entityClass       = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    @Resource
    private SqlSession<T, KEY> sqlSession;

    @Override
    public KEY insert(T entity) {
        if (entity == null) {
            return null;
        }
        return sqlSession.insertReturnPK(entity);
    }

    @Override
    public int insert(List<T> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return 0;
        }
        int size = entities.size();
        if (size <= BATCH_INSERT_SIZE) {
            return sqlSession.insert(entities);
        } else {
            int count = 0;
            int index = 0;
            List<T> segements = ListUtils.newArrayList();
            for (T entity : entities) {
                segements.add(entity);
                index++;
                if (index % BATCH_INSERT_SIZE == 0) {
                    count += sqlSession.insert(segements);
                    segements = ListUtils.newArrayList();
                    index = 0;
                }
            }

            if (CollectionUtils.isNotEmpty(segements)) {
                count += sqlSession.insert(segements);
            }
            return count;
        }
    }

    @Override
    public int deleteByKey(KEY key) {
        return sqlSession.deleteByKey(entityClass, key);
    }

    @Override
    public int deleteByKeys(List<KEY> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return 0;
        }
        return sqlSession.deleteByKeys(entityClass, keys);
    }

    @Override
    public int delete(T condition) {
        if (condition == null) {
            return 0;
        }
        return sqlSession.delete(condition);
    }

    @Override
    public int updateByKey(T entity, KEY key) {
        if (entity == null) {
            return 0;
        }
        return sqlSession.updateByKey(entity, key);
    }

    @Override
    public int updateByKeys(T entity, List<KEY> keys) {
        if (entity == null || CollectionUtils.isEmpty(keys)) {
            return 0;
        }
        return sqlSession.updateByKeys(entity, keys);
    }

    @Override
    public int update(T entity, T condition) {
        if (entity == null || condition == null) {
            return 0;
        }
        return sqlSession.update(entity, condition);
    }

    @Override
    public T selectByKey(KEY key) {
        return sqlSession.selectByKey(entityClass, key);
    }

    @Override
    public List<T> selectListByKeys(List<KEY> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        return sqlSession.selectListByKeys(entityClass, keys);
    }

    @Override
    public T selectOne(T condition) {
        List<T> list = selectList(condition, 1, 1);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public List<T> selectList(T condition, int currentPage, int pageSize) {
        if (condition == null || currentPage <= 0 || pageSize <= 0) {
            return null;
        }
        return sqlSession.selectList(condition, currentPage, pageSize);
    }

    @Override
    public int selectListCount(T condition) {
        if (condition == null) {
            return 0;
        }
        return sqlSession.selectListCount(condition);
    }
}