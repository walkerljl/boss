package org.walkerljl.boss.service.task.model.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskLogStatusEnumTest
 *
 * @author xingxun
 */
public class TaskLogStatusEnumTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("20", TaskLogStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", TaskLogStatusEnum.DELETED.getDescription());

        Assert.assertEquals("30", TaskLogStatusEnum.SUCCESS.getCode());
        Assert.assertEquals("成功", TaskLogStatusEnum.SUCCESS.getDescription());

        Assert.assertEquals("31", TaskLogStatusEnum.FAILURE.getCode());
        Assert.assertEquals("失败", TaskLogStatusEnum.FAILURE.getDescription());

        TaskLogStatusEnum actual = (TaskLogStatusEnum)TaskLogStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(TaskLogStatusEnum.DELETED, actual);

        actual = (TaskLogStatusEnum)TaskLogStatusEnum.values()[0].getEnumObject("30");
        Assert.assertEquals(TaskLogStatusEnum.SUCCESS, actual);

        actual = (TaskLogStatusEnum)TaskLogStatusEnum.values()[0].getEnumObject("31");
        Assert.assertEquals(TaskLogStatusEnum.FAILURE, actual);
    }
}