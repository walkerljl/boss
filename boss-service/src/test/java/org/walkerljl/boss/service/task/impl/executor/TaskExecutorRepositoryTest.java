package org.walkerljl.boss.service.task.impl.executor;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * TaskExecutorRepositoryTest
 *
 * @author xingxun
 */
public class TaskExecutorRepositoryTest extends BaseTaskUnitTest {

    @Test
    public void lookup() {
        TaskExecutor actual = TaskExecutorRepository.getInstance().lookup(null);
        Assert.assertNull(actual);

        TaskExecutorConfig config = new TaskExecutorConfig();
        actual = TaskExecutorRepository.getInstance().lookup(config);
        Assert.assertEquals(TaskExecutorConfig.DEFAULT_EXECUTOR_ID, actual.getId());

        config.setId(TaskStatusEnum.UNPROCESS.getCode());
        actual = TaskExecutorRepository.getInstance().lookup(config);
        Assert.assertEquals(TaskStatusEnum.UNPROCESS.getCode(), actual.getId());

        actual = TaskExecutorRepository.getInstance().lookup(config);
        Assert.assertEquals(TaskStatusEnum.UNPROCESS.getCode(), actual.getId());

        config.setId(TaskStatusEnum.PROCESSING.getCode());
        actual = TaskExecutorRepository.getInstance().lookup(config);
        Assert.assertEquals(TaskStatusEnum.PROCESSING.getCode(), actual.getId());

        config.setId(TaskStatusEnum.FAILURE.getCode());
        actual = TaskExecutorRepository.getInstance().lookup(config);
        Assert.assertEquals(TaskStatusEnum.FAILURE.getCode(), actual.getId());
    }
}