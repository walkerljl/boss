package org.walkerljl.boss.service.task.impl;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * TaskExecutionConfigTest
 *
 * @author xingxun
 */
public class TaskExecutionConfigTest extends BaseTaskUnitTest {

    @Test
    public void getExecutorConfig() {

        TaskStatusEnum taskStatus = TaskStatusEnum.UNPROCESS;
        TaskExecutionConfig taskExecutionConfig = new TaskExecutionConfig();
        taskExecutionConfig.getExecutorConfig(taskStatus);

        taskStatus = TaskStatusEnum.FAILURE;
        taskExecutionConfig.getExecutorConfig(taskStatus);
    }
}