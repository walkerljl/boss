package org.walkerljl.boss.service.task.impl;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.dao.daointerface.task.TaskDAO;
import org.walkerljl.boss.dao.daointerface.task.TaskParamDAO;
import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.task.TaskBroker;
import org.walkerljl.boss.service.task.impl.abstracts.AbstractTaskBroker;
import org.walkerljl.boss.service.task.impl.converter.TaskDOConverter;
import org.walkerljl.boss.service.task.impl.converter.TaskParamDOConverter;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * DefaultTaskBroker
 *
 * @author xingxun
 */
public class DefaultTaskBroker extends AbstractTaskBroker implements TaskBroker {

    private ModelConverter<Task, TaskDO>           taskDOConverter      = new TaskDOConverter();
    private ModelConverter<TaskParam, TaskParamDO> taskParamDOConverter = new TaskParamDOConverter();
    private TaskDAO                                taskDAO;
    private TaskParamDAO                           taskParamDAO;

    public DefaultTaskBroker(TaskDAO taskDAO, TaskParamDAO taskParamDAO) {
        this.taskDAO = taskDAO;
        this.taskParamDAO = taskParamDAO;
    }

    @Override
    public String submit0(Task task) {
        /**
         * 1、保存任务
         * 2、保存任务执行参数
         * 3、更新任务状态为业务需求状态
         * 备注：不需要事务，无锁处理。
         */
        TaskDO taskDO = taskDOConverter.toB(task);
        if (taskDO == null) {
            return null;
        }
        String originalTaskStatus = taskDO.getStatus();
        if (CollectionUtil.isNotEmpty(task.getParams())) {
            taskDO.setStatus(TaskStatusEnum.INITIALIZE.getCode());
        }
        taskDAO.save(taskDO);
        String taskId = String.valueOf(taskDO.getId());

        //保存任务参数
        if (CollectionUtil.isEmpty(task.getParams())) {
            return taskId;
        }
        for (TaskParam taskParam : task.getParams()) {
            if (taskParam == null) {
                continue;
            }
            taskParam.setBizCode(task.getBizCode());
            taskParam.setBizId(task.getBizId());
            taskParam.setTaskId(taskId);
            taskParamDAO.save(taskParamDOConverter.toB(taskParam));
        }

        //恢复任务状态
        taskDAO.updateStatus(taskDO.getBizCode(), taskDO.getBizId(), taskDO.getId(), originalTaskStatus);

        return taskId;
    }

    @Override
    public void markTaskToExecuted0(String bizCode, String bizId, String taskId) {
        taskDAO.markExecutedSuccess(bizCode, bizId, Long.valueOf(taskId));
    }
}