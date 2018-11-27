package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * TaskBuilder
 *
 * @author xingxun
 */
public class TaskBuilder {

    private static final int DEFAULT_ATTEMPTS = 0;
    private static final int DEFAULT_MAX_ATTEMPTS = 10;
    private static final String DEFAULT_RETRY_RULE = "10";

    public static Task buildTask(String bizCode, String bizId, String handlerId, TaskPriorityEnum priority, String creator) {
        return buildTask(bizCode, bizId, handlerId, priority, null, creator);
    }

    public static Task buildTask(String bizCode, String bizId, String handlerId, TaskPriorityEnum priority, List<TaskParam> taskParams, String creator) {
        Task task = new Task();

        task.setBizCode(bizCode);
        task.setBizId(bizId);
        task.setHandlerId(handlerId);
        task.setPriority(priority);
        task.setAttempts(DEFAULT_ATTEMPTS);
        task.setMaxAttempts(DEFAULT_MAX_ATTEMPTS);
        task.setRetryRule(DEFAULT_RETRY_RULE);
        task.setLastRetryTime(new Date());
        task.setNextRetryTime(task.getLastRetryTime());
        task.setParams(taskParams);
        task.setStatus(TaskStatusEnum.UNPROCESS);
        task.setCreator(creator);
        task.setCreatedTime(new Date());
        task.setModifier(task.getCreator());
        task.setModifiedTime(task.getCreatedTime());
        return task;
    }
}