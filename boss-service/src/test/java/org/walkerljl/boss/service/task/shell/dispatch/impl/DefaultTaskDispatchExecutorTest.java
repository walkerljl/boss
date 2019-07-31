package org.walkerljl.boss.service.task.shell.dispatch.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.impl.DefaultTaskService;
import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchExecutor;
import org.walkerljl.boss.service.task.shell.dispatch.exception.TaskExecutionException;

/**
 * DefaultTaskDispatchExecutorTest
 *
 * @author xingxun
 */
public class DefaultTaskDispatchExecutorTest extends BaseTaskUnitTest {

    @Test
    public void execute() {

        TaskService taskService = new DefaultTaskService();
        TaskExecutionConfig executionConfig = new TaskExecutionConfig();
        String taskId = null;
        TaskDispatchExecutor dispatchExecutor = new DefaultTaskDispatchExecutor(taskService, executionConfig);
        dispatchExecutor.execute(taskId);

        taskId = String.valueOf(new DefaultSplitedTaskItem("bizCode","bizId","taskId", TaskStatusEnum.UNPROCESS.getCode()));
        dispatchExecutor = new DefaultTaskDispatchExecutor(taskService, executionConfig);
        dispatchExecutor.execute(taskId);

        executionConfig = null;
        dispatchExecutor = new DefaultTaskDispatchExecutor(taskService, executionConfig);
        boolean flag = false;
        try {
            dispatchExecutor.execute(taskId);
        } catch (TaskExecutionException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }
}