package org.walkerljl.boss.dao.daointerface.task;

import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;

/**
 * 任务执行日志数据访问层接口
 *
 * @author xingxun
 */
public interface TaskLogDAO {

    /**
     * 添加任务执行日志
     *
     * @param taskLog 任务执行日志
     */
    void save(TaskLogDO taskLog);
}