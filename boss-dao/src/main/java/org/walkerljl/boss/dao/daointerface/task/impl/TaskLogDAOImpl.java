package org.walkerljl.boss.dao.daointerface.task.impl;

import org.walkerljl.boss.dao.daointerface.task.TaskLogDAO;
import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * TaskLogDAOImpl
 *
 * @author xingxun
 */
public class TaskLogDAOImpl extends BaseDAOImpl<Long, TaskLogDO> implements TaskLogDAO {

    public TaskLogDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.task.TaskLogDAO";
    }

    @Override
    public void save(TaskLogDO taskLog) {
        insert(getNameSpace("save"), taskLog);
    }
}