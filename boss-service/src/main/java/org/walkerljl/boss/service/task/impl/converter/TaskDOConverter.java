package org.walkerljl.boss.service.task.impl.converter;

import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.converter.abstracts.AbstractDOConverter;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * 任务DO模型转换
 *
 * @author xingxun
 */
public class TaskDOConverter extends AbstractDOConverter<Task, TaskDO> implements ModelConverter<Task, TaskDO> {

    @Override
    public Task toA0(TaskDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        Task outputModel = new Task();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setPriority((TaskPriorityEnum) TaskPriorityEnum.values()[0].getEnumObject(String.valueOf(inputModel.getPriority())));
        outputModel.setHandlerId(inputModel.getHandlerId());
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setMaxAttempts(inputModel.getMaxAttempts());
        outputModel.setRetryRule(inputModel.getRetryRule());
        outputModel.setLastRetryTime(inputModel.getLastRetryTime());
        outputModel.setNextRetryTime(inputModel.getNextRetryTime());
        outputModel.setStatus(TaskStatusEnum.values()[0].getEnumObject(String.valueOf(inputModel.getStatus())));
        return null;
    }

    @Override
    public TaskDO toB0(Task inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskDO outputModel = new TaskDO();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setPriority(Integer.parseInt(inputModel.getPriority().getCode()));
        outputModel.setHandlerId(inputModel.getHandlerId());
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setMaxAttempts(inputModel.getMaxAttempts());
        outputModel.setRetryRule(inputModel.getRetryRule());
        outputModel.setLastRetryTime(inputModel.getLastRetryTime());
        outputModel.setNextRetryTime(inputModel.getNextRetryTime());
        return null;
    }
}