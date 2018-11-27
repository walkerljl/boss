package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;
import org.walkerljl.boss.service.task.shell.dispatch.impl.DefaultSplitedTaskItem;
import org.walkerljl.boss.support.common.util.DateUtil;

/**
 * TaskUtilTest
 *
 * @author xingxun
 */
public class TaskUtilTest extends BaseTaskUnitTest {

    @Test
    public void buildExecutionContext() {

        TaskExecutionConfig taskExecutionConfig = new TaskExecutionConfig();
        DefaultSplitedTaskItem splitedTaskItem = new DefaultSplitedTaskItem("bizCode", "bizId",
                "taskId", TaskStatusEnum.UNPROCESS.getCode());
        TaskExecutionContext actual = TaskUtil.buildExecutionContext(null, splitedTaskItem);
        Assert.assertNull(actual);

        actual = TaskUtil.buildExecutionContext(taskExecutionConfig, null);
        Assert.assertNull(actual);

        actual = TaskUtil.buildExecutionContext(null, null);
        Assert.assertNull(actual);

        actual = TaskUtil.buildExecutionContext(taskExecutionConfig, splitedTaskItem);
        Assert.assertEquals(splitedTaskItem.getBizCode(), actual.getAttribute(TaskExecutionContext.TASK_BIZ_CODE));
        Assert.assertEquals(splitedTaskItem.getBizId(), actual.getAttribute(TaskExecutionContext.TASK_BIZ_ID));
        Assert.assertEquals(splitedTaskItem.getTaskId(), actual.getAttribute(TaskExecutionContext.TASK_ID));
        Assert.assertEquals(splitedTaskItem.getTaskStatus(), actual.getAttribute(TaskExecutionContext.TASK_STATUS));

        Assert.assertEquals(String.valueOf(taskExecutionConfig.getTaskExecutionTimeout()),
                String.valueOf(actual.getAttribute(TaskExecutionContext.TASK_TIME_OUT)));
    }

    @Test
    public void isCanExecute() {

        Task task = null;
        TaskExecutionConfig config = null;
        boolean actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task = new Task();
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setStatus(TaskStatusEnum.PROCESSED);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setStatus(TaskStatusEnum.DELETED);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setAttempts(10);
        task.setMaxAttempts(10);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setAttempts(11);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setAttempts(1);
        task.setStatus(TaskStatusEnum.UNPROCESS);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertTrue(actual);

        task.setStatus(TaskStatusEnum.FAILURE);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertTrue(actual);

        task.setNextRetryTime(DateUtil.modifyTime(new Date(), TimeUnit.DAYS, -1));
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertTrue(actual);

        task.setNextRetryTime(DateUtil.modifyTime(new Date(), TimeUnit.HOURS, 1));
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        task.setStatus(TaskStatusEnum.PROCESSING);
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertTrue(actual);

        task.setLastRetryTime(new Date());
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);

        TaskExecutionConfig defaultConfig = new TaskExecutionConfig();
        task.setLastRetryTime(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, -((int)defaultConfig.getTaskExecutionTimeout() + 10)));
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertTrue(actual);

        task.setLastRetryTime(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, -((int)defaultConfig.getTaskExecutionTimeout() -5)));
        actual = TaskUtil.isCanExecute(task, config);
        Assert.assertFalse(actual);
    }
}