package org.walkerljl.boss.service.task;

import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.toolkit.standard.resource.Resource;

/**
 * Task listener
 *
 * @author xingxun
 */
public interface TaskListener extends Resource {

    /**
     * Response to task which is running.
     *
     * @param context Context
     */
    void onRunning(TaskExecutionContext context);

    /**
     * Response to task which has completed.
     *
     * @param context Context
     */
    void onCompleted(TaskExecutionContext context);

    /**
     * Response to task which has failure.
     *
     * @param context Context
     */
    void onError(TaskExecutionContext context);

    /**
     * Response to task which has abort.
     *
     * @param context Context
     */
    void onAbort(TaskExecutionContext context);
}