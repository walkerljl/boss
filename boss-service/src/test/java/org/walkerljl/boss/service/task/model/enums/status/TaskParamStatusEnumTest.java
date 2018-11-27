package org.walkerljl.boss.service.task.model.enums.status;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * TaskParamStatusEnumTest
 *
 * @author xingxun
 */
public class TaskParamStatusEnumTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        Assert.assertEquals("10", TaskParamStatusEnum.NORMAL.getCode());
        Assert.assertEquals("正常", TaskParamStatusEnum.NORMAL.getDescription());

        Assert.assertEquals("20", TaskParamStatusEnum.DELETED.getCode());
        Assert.assertEquals("已删除", TaskParamStatusEnum.DELETED.getDescription());


        TaskParamStatusEnum actual = (TaskParamStatusEnum)TaskParamStatusEnum.values()[0].getEnumObject("10");
        Assert.assertEquals(TaskParamStatusEnum.NORMAL, actual);

        actual = (TaskParamStatusEnum)TaskParamStatusEnum.values()[0].getEnumObject("20");
        Assert.assertEquals(TaskParamStatusEnum.DELETED, actual);
    }
}