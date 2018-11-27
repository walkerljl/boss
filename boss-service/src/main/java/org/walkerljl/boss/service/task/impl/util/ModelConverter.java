package org.walkerljl.boss.service.task.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;
import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskParamStatusEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * 核型领域模型、DO转换器
 *
 * @author xingxun
 */
public class ModelConverter {

    /**
     * toTask
     *
     * @param inputModel
     * @return
     */
    public static Task toTask(TaskDO inputModel) {
        if (inputModel == null) {
            return null;
        }

        Task resultModel = new Task();
        resultModel.initBaseInfo(inputModel, TaskStatusEnum.values()[0].getEnumObject(String.valueOf(inputModel.getStatus())));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setPriority((TaskPriorityEnum) TaskPriorityEnum.values()[0].getEnumObject(String.valueOf(inputModel.getPriority())));
        resultModel.setHandlerId(inputModel.getHandlerId());
        resultModel.setAttempts(inputModel.getAttempts());
        resultModel.setMaxAttempts(inputModel.getMaxAttempts());
        resultModel.setRetryRule(inputModel.getRetryRule());
        resultModel.setLastRetryTime(inputModel.getLastRetryTime());
        resultModel.setNextRetryTime(inputModel.getNextRetryTime());

        return resultModel;
    }

    /**
     * toTasks
     *
     * @param inputModels
     * @return
     */
    public static List<Task> toTasks(List<TaskDO> inputModels) {
        if (CollectionUtil.isEmpty(inputModels)) {
            return null;
        }
        List<Task> resultModels = new ArrayList<>(inputModels.size());
        for (TaskDO inputModel : inputModels) {
            Task resultModel = toTask(inputModel);
            if (resultModel != null) {
                resultModels.add(resultModel);
            }
        }
        return resultModels;
    }

    /**
     * toTaskDO
     *
     * @param inputModel
     * @return
     */
    public static TaskDO toTaskDO(Task inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskDO resultModel = new TaskDO();
        inputModel.initBaseDOInfo(resultModel);
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setPriority(Integer.parseInt(inputModel.getPriority().getCode()));
        resultModel.setHandlerId(inputModel.getHandlerId());
        resultModel.setAttempts(inputModel.getAttempts());
        resultModel.setMaxAttempts(inputModel.getMaxAttempts());
        resultModel.setRetryRule(inputModel.getRetryRule());
        resultModel.setLastRetryTime(inputModel.getLastRetryTime());
        resultModel.setNextRetryTime(inputModel.getNextRetryTime());
        return resultModel;
    }

    /**
     * toTaskParam
     *
     * @param inputModel
     * @return
     */
    public static TaskParam toTaskParam(TaskParamDO inputModel) {
        if (inputModel == null) {
            return null;
        }

        TaskParam resultModel = new TaskParam();
        resultModel.initBaseInfo(inputModel, TaskParamStatusEnum.values()[0].getEnumObject(inputModel.getStatus()));
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setTaskId(inputModel.getTaskId());
        resultModel.setValue(inputModel.getValue());
        return resultModel;
    }

    /**
     * toTaskParam
     *
     * @param inputModels
     * @return
     */
    public static List<TaskParam> toTaskParams(List<TaskParamDO> inputModels) {
        if (CollectionUtil.isEmpty(inputModels)) {
            return null;
        }
        List<TaskParam> resultModels = new ArrayList<>(inputModels.size());
        for (TaskParamDO inputModel : inputModels) {
            TaskParam resultModel = toTaskParam(inputModel);
            if (resultModel != null) {
                resultModels.add(resultModel);
            }
        }
        return resultModels;
    }

    /**
     * toTaskParamDO
     *
     * @param inputModel
     * @return
     */
    public static TaskParamDO toTaskParamDO(TaskParam inputModel) {
        if (inputModel == null) {
            return null;
        }

        TaskParamDO resultModel = new TaskParamDO();
        inputModel.initBaseDOInfo(resultModel);
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setTaskId(inputModel.getTaskId());
        resultModel.setValue(inputModel.getValue());
        return resultModel;
    }

    /**
     * toTaskLogDO
     *
     * @param inputModel
     * @return
     */
    public static TaskLogDO toTaskLogDO(TaskLog inputModel) {
        if (inputModel == null) {
            return null;
        }
        TaskLogDO resultModel = new TaskLogDO();
        inputModel.initBaseDOInfo(resultModel);
        resultModel.setBizCode(inputModel.getBizCode());
        resultModel.setBizId(inputModel.getBizId());
        resultModel.setTaskId(inputModel.getTaskId());
        resultModel.setAttempts(inputModel.getAttempts());
        resultModel.setDescription(inputModel.getDescription());
        return resultModel;
    }
}