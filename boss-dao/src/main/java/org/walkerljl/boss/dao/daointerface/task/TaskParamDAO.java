package org.walkerljl.boss.dao.daointerface.task;

import java.util.List;

import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;

/**
 * 任务执行参数数据访问层接口
 *
 * @author xingxun
 */
public interface TaskParamDAO {

    /**
     * 添加任务执行参数
     *
     * @param taskParam 任务执行参数
     */
    void save(TaskParamDO taskParam);

    /**
     * 根据业务编码、业务ID、任务ID查询任务执行参数列表
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     * @return
     */
    List<TaskParamDO> list(String bizCode, String bizId, Long taskId);
}