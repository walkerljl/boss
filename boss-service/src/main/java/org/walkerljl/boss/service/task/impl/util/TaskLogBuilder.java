package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;

import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.enums.status.TaskLogStatusEnum;

/**
 * TaskLogBuilder
 *
 * @author xingxun
 */
public class TaskLogBuilder {

    /**
     * buildBaseTaskLog
     *
     * @param task
     * @return
     */
    private static TaskLog buildBaseTaskLog(Task task) {
        TaskLog taskLog = new TaskLog();
        taskLog.setBizCode(task.getBizCode());
        taskLog.setBizId(task.getBizId());
        taskLog.setTaskId(task.getId());
        taskLog.setAttempts(task.getAttempts());
        taskLog.setCreator(task.getCreator());
        taskLog.setModifier(taskLog.getCreator());
        taskLog.setCreatedTime(new Date());
        taskLog.setModifiedTime(taskLog.getCreatedTime());

        return taskLog;
    }

    /**
     * buildFailureTaskLog
     *
     * @param task
     * @param errorMsg
     * @return
     */
    public static TaskLog buildFailureTaskLog(Task task, String errorMsg) {
        if (task == null) {
            return null;
        }
        TaskLog taskLog = buildBaseTaskLog(task);
        taskLog.setStatus(TaskLogStatusEnum.FAILURE);
        taskLog.setDescription(errorMsg);
        return taskLog;
    }

    /**
     * buildSuccessTaskLog
     *
     * @param task
     * @return
     */
    public static TaskLog buildSuccessTaskLog(Task task) {
        if (task == null) {
            return null;
        }
        TaskLog taskLog = buildBaseTaskLog(task);
        taskLog.setStatus(TaskLogStatusEnum.SUCCESS);
        return taskLog;
    }
}