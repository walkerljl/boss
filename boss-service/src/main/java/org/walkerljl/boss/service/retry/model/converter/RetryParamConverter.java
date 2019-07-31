package org.walkerljl.boss.service.retry.model.converter;

import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.converter.abstracts.AbstractCoreConverter;
import org.walkerljl.boss.service.retry.model.RetryParam;
import org.walkerljl.boss.service.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.status.TaskParamStatusEnum;

/**
 * 重试参数模型转换器
 *
 * @author xingxun
 */
public class RetryParamConverter extends AbstractCoreConverter<RetryParam, TaskParam> implements ModelConverter<RetryParam, TaskParam> {

    @Override
    public RetryParam toA0(TaskParam inputModel) {
        RetryParam outputModel = new RetryParam();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setValue(inputModel.getValue());
        outputModel.setStatus(RetryParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));

        return outputModel;
    }

    @Override
    public TaskParam toB0(RetryParam inputModel) {
        TaskParam outputModel = new TaskParam();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setValue(inputModel.getValue());
        outputModel.setStatus(TaskParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));
        return outputModel;
    }
}