package org.walkerljl.boss.service.retry.model.converter;

import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.converter.abstracts.AbstractCoreConverter;
import org.walkerljl.boss.service.retry.model.RetryParam;
import org.walkerljl.boss.service.retry.model.RetryTask;
import org.walkerljl.boss.service.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * 模型转换器
 *
 * @author xingxun
 */
public class RetryTaskConverter extends AbstractCoreConverter<RetryTask, Task> implements ModelConverter<RetryTask, Task> {

    private ModelConverter<RetryParam, TaskParam> retryParamConverter = new RetryParamConverter();

    @Override
    public RetryTask toA0(Task inputModel) {
        RetryTask outputModel = new RetryTask();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setHandlerId(inputModel.getHandlerId());
        outputModel.setPriority((RetryPriorityEnum) RetryPriorityEnum.values()[0].getEnumObject(inputModel.getPriority().getCode()));
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setMaxAttempts(inputModel.getMaxAttempts());
        outputModel.setRetryRule(inputModel.getRetryRule());
        outputModel.setLastRetryTime(inputModel.getLastRetryTime());
        outputModel.setNextRetryTime(inputModel.getNextRetryTime());
        outputModel.setStatus(TaskStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));

        outputModel.setParams(retryParamConverter.toAList(inputModel.getParams()));

        return outputModel;
    }

    @Override
    public Task toB0(RetryTask inputModel) {
        Task outputModel = new Task();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setHandlerId(inputModel.getHandlerId());
        outputModel.setPriority((TaskPriorityEnum) TaskPriorityEnum.values()[0].getEnumObject(inputModel.getPriority().getCode()));
        outputModel.setAttempts(inputModel.getAttempts());
        outputModel.setMaxAttempts(inputModel.getMaxAttempts());
        outputModel.setRetryRule(inputModel.getRetryRule());
        outputModel.setLastRetryTime(inputModel.getLastRetryTime());
        outputModel.setNextRetryTime(inputModel.getNextRetryTime());
        outputModel.setStatus(TaskStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));

        outputModel.setParams(retryParamConverter.toBList(inputModel.getParams()));
        return outputModel;
    }
}