package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.enums.status.TaskLogStatusEnum;
import org.walkerljl.boss.support.common.util.DateUtil;

/**
 * TaskLogBuilderTest
 *
 * @author xingxun
 */
public class TaskLogBuilderTest extends BaseTaskUnitTest {

    private Task newTask(int index) {
        Task task = new Task();
        task.setBizCode("bizCode" + index);
        task.setBizId("bizId" + index);
        task.setId(String.valueOf(index));
        task.setAttempts(2 + index);
        task.setCreator("creator" + index);
        task.setCreatedTime(new Date());
        task.setModifier(task.getCreator());
        task.setModifiedTime(task.getCreatedTime());

        return task;
    }

    private void asserts(Task expected, TaskLog actual, TaskLogStatusEnum taskLogStatus, String desc) {
        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(expected.getId(), actual.getTaskId());
        Assert.assertEquals(expected.getAttempts(), actual.getAttempts());
        Assert.assertEquals(desc, actual.getDescription());
        Assert.assertEquals(taskLogStatus.getCode(), actual.getStatus().getCode());
        Assert.assertEquals(expected.getCreator(), actual.getCreator());
        Assert.assertEquals(DateUtil.dateFormat(expected.getCreatedTime(), DateUtil.FOPRMAT_YYYY_MM_DD_HH_MM),
                DateUtil.dateFormat(actual.getCreatedTime(), DateUtil.FOPRMAT_YYYY_MM_DD_HH_MM));
        Assert.assertEquals(expected.getModifier(), actual.getModifier());
        Assert.assertEquals(DateUtil.dateFormat(expected.getModifiedTime(), DateUtil.FOPRMAT_YYYY_MM_DD_HH_MM),
                DateUtil.dateFormat(actual.getModifiedTime(), DateUtil.FOPRMAT_YYYY_MM_DD_HH_MM));
    }

    @Test
    public void buildFailureTaskLog() {

        String desc = "errorMsg";
        Task task = newTask(1);
        TaskLog actual = TaskLogBuilder.buildFailureTaskLog(task, desc);
        asserts(task, actual, TaskLogStatusEnum.FAILURE, desc);
    }

    @Test
    public void buildSuccessTaskLog() {

        String desc = null;
        Task task = newTask(1);
        TaskLog actual = TaskLogBuilder.buildSuccessTaskLog(task);
        asserts(task, actual, TaskLogStatusEnum.SUCCESS, desc);
    }
}