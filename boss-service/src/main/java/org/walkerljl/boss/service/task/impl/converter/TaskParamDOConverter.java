package org.walkerljl.boss.service.task.impl.converter;

import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.service.converter.ModelConverter;
import org.walkerljl.boss.service.converter.abstracts.AbstractDOConverter;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.status.TaskParamStatusEnum;

/**
 * 任务参数DO模型转换
 *
 * @author xingxun
 */
public class TaskParamDOConverter extends AbstractDOConverter<TaskParam, TaskParamDO> implements ModelConverter<TaskParam, TaskParamDO> {

    @Override
    public TaskParam toA0(TaskParamDO inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskParam outputModel = new TaskParam();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setValue(inputModel.getValue());
        outputModel.setStatus(TaskParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        return outputModel;
    }

    @Override
    public TaskParamDO toB0(TaskParam inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskParamDO outputModel = new TaskParamDO();
        outputModel.setBizCode(inputModel.getBizCode());
        outputModel.setBizId(inputModel.getBizId());
        outputModel.setTaskId(inputModel.getTaskId());
        outputModel.setValue(inputModel.getValue());
        outputModel.setStatus(inputModel.getStatus() == null ? null : inputModel.getStatus().getCode());

        return outputModel;
    }
}