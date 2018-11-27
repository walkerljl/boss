package org.walkerljl.boss.service.task.shell.dispatch.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchLoader;

/**
 * DefaultTaskDispatchLoaderTest
 *
 * @author xingxun
 */
public class DefaultTaskDispatchLoaderTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        SplitedTaskItem splitedTaskItem = null;
        TaskDispatchLoader dispatchLoader = new DefaultTaskDispatchLoader();
        List<String> actual = dispatchLoader.load(splitedTaskItem);
        Assert.assertNull(actual);

        splitedTaskItem = new DefaultSplitedTaskItem("bizCode", "bizId", "taskId", "taskStatus");
        actual = dispatchLoader.load(splitedTaskItem);
        Assert.assertEquals(Arrays.asList(String.valueOf(splitedTaskItem)), actual);
    }
}