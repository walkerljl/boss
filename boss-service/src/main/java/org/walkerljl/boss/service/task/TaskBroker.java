package org.walkerljl.boss.service.task;

import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.toolkit.standard.machine.Machine;

/**
 * 任务中间人
 *
 * @author xingxun
 */
public interface TaskBroker extends Machine {

    /**
     * 提交一个任务
     *
     * @param task 任务
     * @return 任务ID
     */
    String submit(Task task);

    /**
     * 标注任务已经执行完成
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     */
    void markTaskToExecuted(String bizCode, String bizId, String taskId);
}