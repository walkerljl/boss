package org.walkerljl.boss.service.task.model.enums;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskPriorityEnumTest
 *
 * @author xingxun
 */
public class TaskPriorityEnumTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("1", TaskPriorityEnum.HIGH.getCode());
        Assert.assertEquals("高", TaskPriorityEnum.HIGH.getDescription());

        Assert.assertEquals("5", TaskPriorityEnum.NORMAL.getCode());
        Assert.assertEquals("中", TaskPriorityEnum.NORMAL.getDescription());

        Assert.assertEquals("9", TaskPriorityEnum.LOW.getCode());
        Assert.assertEquals("低", TaskPriorityEnum.LOW.getDescription());

        TaskPriorityEnum actual = (TaskPriorityEnum)TaskPriorityEnum.values()[0].getEnumObject("1");
        Assert.assertEquals(TaskPriorityEnum.HIGH, actual);

        actual = (TaskPriorityEnum)TaskPriorityEnum.values()[0].getEnumObject("5");
        Assert.assertEquals(TaskPriorityEnum.NORMAL, actual);

        actual = (TaskPriorityEnum)TaskPriorityEnum.values()[0].getEnumObject("9");
        Assert.assertEquals(TaskPriorityEnum.LOW, actual);
    }
}