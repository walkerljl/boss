package org.walkerljl.boss.service.task.impl.converter;

import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;
import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.converter.abstracts.AbstractDOConverter;
import org.walkerljl.boss.service.task.model.TaskLog;

/**
 * 任务日志核心领域模型、DO转换器
 *
 * @author xingxun
 */
public class TaskLogDOConverter extends AbstractDOConverter<TaskLog, TaskLogDO> implements ModelConverter<TaskLog, TaskLogDO> {

    @Override
    public TaskLog toA0(TaskLogDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskLog outputModel = new TaskLog();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setDescription(inputModel.getDescription());
        return outputModel;
    }

    @Override
    public TaskLogDO toB0(TaskLog inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskLogDO outputModel = new TaskLogDO();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setDescription(inputModel.getDescription());
        return outputModel;
    }
}