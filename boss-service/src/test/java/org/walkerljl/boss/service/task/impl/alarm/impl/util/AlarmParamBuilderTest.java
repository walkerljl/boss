package org.walkerljl.boss.service.task.impl.alarm.impl.util;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.impl.alarm.AlarmInfo;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.support.common.util.ThrowableUtil;

/**
 * AlarmParamBuilderTest
 *
 * @author xingxun
 */
public class AlarmParamBuilderTest extends BaseTaskUnitTest {

    @Test
    public void buildAlarmInfo() {
        TaskExecutionContext context = null;
        AlarmInfo actual = AlarmParamBuilder.buildAlarmInfo(context);
        Assert.assertNull(actual);

        context = new TaskExecutionContext();
        actual = AlarmParamBuilder.buildAlarmInfo(context);
        Assert.assertNull(actual);

        Task task = new Task();
        task.setBizCode("bizCode");
        task.setBizId("bizId");
        task.setId("id");
        context.setAttribute(TaskExecutionContext.TASK, task);

        Throwable e = new RuntimeException("Test");
        context.setAttribute(TaskExecutionContext.EXECUTION_RESULT_THROABLE, e);

        actual = AlarmParamBuilder.buildAlarmInfo(context);
        Assert.assertEquals(String.format("%s:%s:%s", task.getBizCode(), task.getBizId(), task.getId()), actual.getKey());
        Assert.assertEquals(ThrowableUtil.getMessage(e), actual.getDescription());
    }
}