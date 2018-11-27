package org.walkerljl.boss.dao.daointerface.task.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.task.TaskDAO;
import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;
import org.walkerljl.toolkit.standard.model.paging.Paginator;

/**
 * TaskDAOImpl
 *
 * @author xingxun
 */
public class TaskDAOImpl extends BaseDAOImpl<Long, TaskDO> implements TaskDAO {

    public TaskDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.task.TaskDAO";
    }

    @Override
    public void save(TaskDO task) {
        insert(getNameSpace("save"), task);
    }

    @Override
    public int lock(String bizCode, String bizId, Long id, Long timeout) {
        Map<String, Object> condition = new HashMap<>(4);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("id", id);
        condition.put("timeout", timeout);

        return update(getNameSpace("lock"), condition);
    }

    @Override
    public void markExecutedSuccess(String bizCode, String bizId, Long id) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("id", id);

        update(getNameSpace("markExecutedSuccess"), condition);
    }

    @Override
    public void markExecutedFailure(String bizCode, String bizId, Long id, Date nextRetryTime, String errorMsg) {
        Map<String, Object> condition = new HashMap<>(5);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("id", id);
        condition.put("nextRetryTime", nextRetryTime);
        condition.put("errorMsg", errorMsg);

        update(getNameSpace("markExecutedFailure"), condition);
    }

    @Override
    public void updateStatus(String bizCode, String bizId, Long id, String status) {
        Map<String, Object> condition = new HashMap<>(4);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("id", id);
        condition.put("status", status);

        update(getNameSpace("updateStatus"), condition);
    }

    @Override
    public TaskDO get(String bizCode, String bizId, Long id) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("id", id);
        return select(getNameSpace("get"), condition);
    }

    @Override
    public List<TaskDO> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("loadInterval", loadInterval);
        Paginator<TaskDO> paginator = new Paginator<>(currentPage, pageSize);
        condition.put("beginIndex", paginator.getBeginIndex());
        condition.put("pageSize", paginator.getPageSize());

        return selectList(getNameSpace("listUnprocessTasks"), condition);
    }

    @Override
    public List<TaskDO> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("loadInterval", loadInterval);
        Paginator<TaskDO> paginator = new Paginator<>(currentPage, pageSize);
        condition.put("beginIndex", paginator.getBeginIndex());
        condition.put("pageSize", paginator.getPageSize());

        return selectList(getNameSpace("listFailureTasks"), condition);
    }

    @Override
    public List<TaskDO> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
        Map<String, Object> condition = new HashMap<>(4);
        condition.put("loadInterval", loadInterval);
        condition.put("retryTimeout", retryTimeout);
        Paginator<TaskDO> paginator = new Paginator<>(currentPage, pageSize);
        condition.put("beginIndex", paginator.getBeginIndex());
        condition.put("pageSize", paginator.getPageSize());

        return selectList(getNameSpace("listTimeoutTasks"), condition);
    }
}