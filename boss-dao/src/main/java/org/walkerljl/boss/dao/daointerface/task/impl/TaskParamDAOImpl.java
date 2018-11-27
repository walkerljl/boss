package org.walkerljl.boss.dao.daointerface.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.walkerljl.boss.dao.daointerface.task.TaskParamDAO;
import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.support.dao.impl.BaseDAOImpl;

/**
 * TaskParamDAOImpl
 *
 * @author xingxun
 */
public class TaskParamDAOImpl extends BaseDAOImpl<TaskParamDO, Long> implements TaskParamDAO {

    public TaskParamDAOImpl() {
        super.baseNameSpace = "org.walkerljl.boss.dao.daointerface.task.TaskParamDAO";
    }

    @Override
    public void save(TaskParamDO taskParam) {
        insert(getNameSpace("save"), taskParam);
    }

    @Override
    public List<TaskParamDO> list(String bizCode, String bizId, Long taskId) {
        Map<String, Object> condition = new HashMap<>(3);
        condition.put("bizCode", bizCode);
        condition.put("bizId", bizId);
        condition.put("taskId", taskId);

        return selectList(getNameSpace("list"), condition);
    }
}