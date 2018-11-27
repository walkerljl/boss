package org.walkerljl.boss.service.task.model.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskStatusEnumTest
 *
 * @author xingxun
 */
public class TaskStatusEnumTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("20", TaskStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", TaskStatusEnum.DELETED.getDescription());

        Assert.assertEquals("30", TaskStatusEnum.UNPROCESS.getCode());
        Assert.assertEquals("未处理", TaskStatusEnum.UNPROCESS.getDescription());

        Assert.assertEquals("31", TaskStatusEnum.PROCESSING.getCode());
        Assert.assertEquals("处理中", TaskStatusEnum.PROCESSING.getDescription());

        Assert.assertEquals("32", TaskStatusEnum.PROCESSED.getCode());
        Assert.assertEquals("已处理", TaskStatusEnum.PROCESSED.getDescription());

        Assert.assertEquals("33", TaskStatusEnum.FAILURE.getCode());
        Assert.assertEquals("处理失败", TaskStatusEnum.FAILURE.getDescription());

        TaskStatusEnum actual = (TaskStatusEnum)TaskStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(TaskStatusEnum.DELETED, actual);

        actual = (TaskStatusEnum)TaskStatusEnum.values()[0].getEnumObject("30");
        Assert.assertEquals(TaskStatusEnum.UNPROCESS, actual);

        actual = (TaskStatusEnum)TaskStatusEnum.values()[0].getEnumObject("31");
        Assert.assertEquals(TaskStatusEnum.PROCESSING, actual);

        actual = (TaskStatusEnum)TaskStatusEnum.values()[0].getEnumObject("32");
        Assert.assertEquals(TaskStatusEnum.PROCESSED, actual);

        actual = (TaskStatusEnum)TaskStatusEnum.values()[0].getEnumObject("33");
        Assert.assertEquals(TaskStatusEnum.FAILURE, actual);
    }
}