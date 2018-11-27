package org.walkerljl.boss.service.task.shell.dispatch.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;

/**
 * DefaultSplitedTaskItemTest
 *
 * @author xingxun
 */
public class DefaultSplitedTaskItemTest extends BaseTaskUnitTest {

    @Test
    public void parse() {

        DefaultSplitedTaskItem actual = DefaultSplitedTaskItem.parse(null);
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,bizId");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,bizId,");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,bizId,taskId");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,bizId,taskId,");
        Assert.assertNull(actual);

        actual = DefaultSplitedTaskItem.parse("bizCode,bizId,taskId,taskStatus");
        Assert.assertEquals("bizCode", actual.getBizCode());
        Assert.assertEquals("bizId", actual.getBizId());
        Assert.assertEquals("taskId", actual.getTaskId());
        Assert.assertEquals("taskStatus", actual.getTaskStatus());

        actual = new DefaultSplitedTaskItem();
        actual.setBizCode("bizCode");
        actual.setBizId("bizId");
        actual.setTaskId("taskId");
        actual.setTaskStatus("taskStatus");
    }
}