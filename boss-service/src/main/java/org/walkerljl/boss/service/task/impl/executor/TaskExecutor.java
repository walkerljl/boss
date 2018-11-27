package org.walkerljl.boss.service.task.impl.executor;

import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.toolkit.standard.machine.Machine;

/**
 * 任务执行器
 *
 * @author xingxun
 */
public interface TaskExecutor extends Machine {

    /**
     * 执行任务
     *
     * @param task 可执行的任务
     */
    void execute(RunnableTask task);
}