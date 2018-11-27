package org.walkerljl.boss.service.retry.model;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.service.retry.model.enums.RetryParamStatusEnum;
import org.walkerljl.boss.service.retry.model.enums.RetryPriorityEnum;
import org.walkerljl.boss.service.retry.model.enums.RetryTaskStatusEnum;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.status.TaskParamStatusEnum;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * 模型转换器
 *
 * @author xingxun
 */
public class ModelConverter {

    public static Task toTask(RetryTask inputModel) {
        if (inputModel == null) {
            return null;
        }

        Task resultModel = new Task();
        resultModel.setId(inputModel.getId());
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setHandlerId(inputModel.getHandlerId());
        resultModel.setPriority((TaskPriorityEnum) TaskPriorityEnum.values()[0].getEnumObject(inputModel.getPriority().getCode()));
        resultModel.setAttempts(inputModel.getAttempts());
        resultModel.setMaxAttempts(inputModel.getMaxAttempts());
        resultModel.setRetryRule(inputModel.getRetryRule());
        resultModel.setLastRetryTime(inputModel.getLastRetryTime());
        resultModel.setNextRetryTime(inputModel.getNextRetryTime());
        resultModel.setRemark(inputModel.getRemark());
        resultModel.setExtInfo(inputModel.getExtInfo());
        resultModel.setStatus(TaskStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));
        resultModel.setCreator(inputModel.getCreator());
        resultModel.setCreatedTime(inputModel.getCreatedTime());
        resultModel.setModifier(inputModel.getModifier());
        resultModel.setModifiedTime(inputModel.getModifiedTime());

        resultModel.setParams(toTaskParams(inputModel.getParams()));

        return resultModel;
    }

    private static TaskParam toTaskParam(RetryParam inputModel) {
        TaskParam resultModel = new TaskParam();
        resultModel.setId(inputModel.getId());
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setTaskId(inputModel.getTaskId());
        resultModel.setValue(inputModel.getValue());
        resultModel.setRemark(inputModel.getRemark());
        resultModel.setExtInfo(inputModel.getExtInfo());
        resultModel.setStatus(TaskParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));
        resultModel.setCreator(inputModel.getCreator());
        resultModel.setCreatedTime(inputModel.getCreatedTime());
        resultModel.setModifier(inputModel.getModifier());
        resultModel.setModifiedTime(inputModel.getModifiedTime());

        return resultModel;
    }

    private static List<TaskParam> toTaskParams(List<RetryParam> inputModels) {
        if (inputModels == null) {
            return null;
        }
        List<TaskParam> resultModels = new ArrayList<>(inputModels.size());
        for (RetryParam inputModel : inputModels) {
            TaskParam resultModel = toTaskParam(inputModel);
            if (resultModel != null) {
                resultModels.add(resultModel);
            }
        }
        return resultModels;
    }

    public static RetryTask toRetryTask(Task inputModel) {
        if (inputModel == null) {
            return null;
        }

        RetryTask resultModel = new RetryTask();
        resultModel.setId(inputModel.getId());
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setHandlerId(inputModel.getHandlerId());
        resultModel.setPriority((RetryPriorityEnum) RetryPriorityEnum.values()[0].getEnumObject(inputModel.getPriority().getCode()));
        resultModel.setAttempts(inputModel.getAttempts());
        resultModel.setMaxAttempts(inputModel.getMaxAttempts());
        resultModel.setRetryRule(inputModel.getRetryRule());
        resultModel.setLastRetryTime(inputModel.getLastRetryTime());
        resultModel.setNextRetryTime(inputModel.getNextRetryTime());
        resultModel.setRemark(inputModel.getRemark());
        resultModel.setExtInfo(inputModel.getExtInfo());
        resultModel.setStatus(RetryTaskStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));
        resultModel.setCreator(inputModel.getCreator());
        resultModel.setCreatedTime(inputModel.getCreatedTime());
        resultModel.setModifier(inputModel.getModifier());
        resultModel.setModifiedTime(inputModel.getModifiedTime());

        resultModel.setParams(toRetryParams(inputModel.getParams()));

        return resultModel;
    }

    private static RetryParam toRetryParam(TaskParam inputModel) {
        RetryParam resultModel = new RetryParam();
        resultModel.setId(inputModel.getId());
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setTaskId(inputModel.getTaskId());
        resultModel.setValue(inputModel.getValue());
        resultModel.setRemark(inputModel.getRemark());
        resultModel.setExtInfo(inputModel.getExtInfo());
        resultModel.setStatus(RetryParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus().getCode()));
        resultModel.setCreator(inputModel.getCreator());
        resultModel.setCreatedTime(inputModel.getCreatedTime());
        resultModel.setModifier(inputModel.getModifier());
        resultModel.setModifiedTime(inputModel.getModifiedTime());

        return resultModel;
    }

    private static List<RetryParam> toRetryParams(List<TaskParam> inputModels) {
        if (inputModels == null) {
            return null;
        }
        List<RetryParam> resultModels = new ArrayList<>(inputModels.size());
        for (TaskParam inputModel : inputModels) {
            RetryParam resultModel = toRetryParam(inputModel);
            if (resultModel != null) {
                resultModels.add(resultModel);
            }
        }
        return resultModels;
    }

}