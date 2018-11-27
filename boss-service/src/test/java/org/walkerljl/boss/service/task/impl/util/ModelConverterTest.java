package org.walkerljl.boss.service.task.impl.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;
import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.dao.dataobject.task.base.BaseTaskDO;
import org.walkerljl.boss.model.enums.base.StatusIEnum;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.base.BaseTaskModel;
import org.walkerljl.boss.service.task.model.base.BaseStdTaskModel;
import org.walkerljl.boss.service.task.model.enums.TaskPriorityEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskLogStatusEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskParamStatusEnum;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * ModelConverterTest
 *
 * @author xingxun
 */
public class ModelConverterTest extends BaseTaskUnitTest {

    private void fillBaseInfo(BaseStdTaskModel expected, StatusIEnum status) {
        expected.setId(String.valueOf(1L));
        expected.setRemark("remark");
        Map<String, String> extInfo = new HashMap<>(2);
        extInfo.put("key1", "value1");
        extInfo.put("key2", "value2");
        expected.setExtInfo(extInfo);
        expected.setStatus(status);
        expected.setCreator("xingxun");
        expected.setCreatedTime(new Date());
        expected.setModifier(expected.getCreator());
        expected.setModifiedTime(expected.getCreatedTime());
    }

    private void fillBaseInfo(BaseTaskDO expected, String status) {
        expected.setId(1L);
        expected.setRemark("remark");
        Map<String, String> extInfo = new HashMap<>(2);
        extInfo.put("key1", "value1");
        extInfo.put("key2", "value2");
        expected.setExtInfo(BaseTaskModel.toJSONString(extInfo));
        expected.setStatus(status);
        expected.setCreator("xingxun");
        expected.setCreatedTime(new Date());
        expected.setModifier(expected.getCreator());
        expected.setModifiedTime(expected.getCreatedTime());
    }

    private void assertBaseInfo(BaseStdTaskModel expected, BaseTaskDO actual) {
        Assert.assertEquals(expected.getId(), String.valueOf(actual.getId()));
        Assert.assertEquals(expected.getRemark(), actual.getRemark());
        Assert.assertEquals(expected.getExtInfo(), BaseTaskModel.parseMap(actual.getExtInfo()));
        Assert.assertEquals(expected.getStatus().getCode(), actual.getStatus());
        Assert.assertEquals(expected.getCreator(), actual.getCreator());
        Assert.assertEquals(expected.getCreatedTime(), actual.getCreatedTime());
        Assert.assertEquals(expected.getModifier(), actual.getModifier());
        Assert.assertEquals(expected.getModifiedTime(), actual.getModifiedTime());
    }

    private void assertBaseInfo(BaseTaskDO expected, BaseStdTaskModel actual) {
        Assert.assertEquals(String.valueOf(expected.getId()), actual.getId());
        Assert.assertEquals(expected.getRemark(), actual.getRemark());
        Assert.assertEquals(expected.getExtInfo(), BaseTaskModel.toJSONString(actual.getExtInfo()));
        Assert.assertEquals(expected.getStatus(), actual.getStatus().getCode());
        Assert.assertEquals(expected.getCreator(), actual.getCreator());
        Assert.assertEquals(expected.getCreatedTime(), actual.getCreatedTime());
        Assert.assertEquals(expected.getModifier(), actual.getModifier());
        Assert.assertEquals(expected.getModifiedTime(), actual.getModifiedTime());
    }

    private TaskDO newTaskDO(int index, TaskPriorityEnum priority) {

        TaskDO result = new TaskDO();
        fillBaseInfo(result, TaskStatusEnum.PROCESSED.getCode());
        result.setBizCode("bizCode" + index);
        result.setBizId("bizId" + index);
        result.setPriority(Integer.parseInt(priority.getCode()));
        result.setHandlerId("handlerId" + index);
        result.setAttempts(index);
        result.setMaxAttempts(10 + index);
        result.setRetryRule("retryRule");
        result.setLastRetryTime(new Date());
        result.setNextRetryTime(new Date());

        return result;
    }

    private Task newTask(int index, String priority) {

        Task result = new Task();
        fillBaseInfo(result, TaskStatusEnum.PROCESSED);
        result.setBizCode("bizCode" + index);
        result.setBizId("bizId" + index);
        result.setPriority((TaskPriorityEnum)TaskPriorityEnum.values()[0].getEnumObject(priority));
        result.setHandlerId("handlerId" + index);
        result.setAttempts(index);
        result.setMaxAttempts(10 + index);
        result.setRetryRule("retryRule");
        result.setLastRetryTime(new Date());
        result.setNextRetryTime(new Date());

        return result;
    }

    private void asserts(TaskDO expected, Task actual) {
        assertBaseInfo(expected, actual);

        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(String.valueOf(expected.getPriority()), actual.getPriority().getCode());
        Assert.assertEquals(expected.getHandlerId(), actual.getHandlerId());
        Assert.assertEquals(expected.getAttempts(), actual.getAttempts());
        Assert.assertEquals(expected.getMaxAttempts(), actual.getMaxAttempts());
        Assert.assertEquals(expected.getRetryRule(), actual.getRetryRule());
        Assert.assertEquals(expected.getLastRetryTime(), actual.getLastRetryTime());
        Assert.assertEquals(expected.getNextRetryTime(), actual.getNextRetryTime());
    }

    private void asserts(Task expected, TaskDO actual) {
        assertBaseInfo(expected, actual);

        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(expected.getPriority().getCode(), String.valueOf(actual.getPriority()));
        Assert.assertEquals(expected.getHandlerId(), actual.getHandlerId());
        Assert.assertEquals(expected.getAttempts(), actual.getAttempts());
        Assert.assertEquals(expected.getMaxAttempts(), actual.getMaxAttempts());
        Assert.assertEquals(expected.getRetryRule(), actual.getRetryRule());
        Assert.assertEquals(expected.getLastRetryTime(), actual.getLastRetryTime());
        Assert.assertEquals(expected.getNextRetryTime(), actual.getNextRetryTime());
    }

    @Test
    public void toTask() {

        TaskDO expected = null;
        Task actual = ModelConverter.toTask(expected);
        Assert.assertNull(actual);

        expected = newTaskDO(1, TaskPriorityEnum.NORMAL);
        actual = ModelConverter.toTask(expected);
        asserts(expected, actual);
    }

    @Test
    public void toTasks() {

        List<TaskDO> expecteds = null;
        List<Task> actuals = ModelConverter.toTasks(expecteds);
        Assert.assertNull(actuals);

        expecteds = new ArrayList<>(3);
        expecteds.add(newTaskDO(1, TaskPriorityEnum.NORMAL));
        expecteds.add(newTaskDO(2, TaskPriorityEnum.HIGH));
        expecteds.add(null);

        actuals = ModelConverter.toTasks(expecteds);
        asserts(expecteds.get(0), actuals.get(0));
        asserts(expecteds.get(1), actuals.get(1));
    }

    @Test
    public void toTaskDO() {

        Task expected = null;
        TaskDO actual = ModelConverter.toTaskDO(expected);
        Assert.assertNull(actual);

        expected = newTask(1, TaskPriorityEnum.NORMAL.getCode());
        actual = ModelConverter.toTaskDO(expected);
        asserts(expected, actual);
    }

    private TaskParamDO newTaskParamDO(int index) {

        TaskParamDO result = new TaskParamDO();
        fillBaseInfo(result, TaskParamStatusEnum.NORMAL.getCode());
        result.setBizCode("bizCode" + index);
        result.setBizId("bizId" + index);
        result.setTaskId("taskId" + index);
        result.setValue("value" + index);

        return result;

    }

    private void asserts(TaskParam expected, TaskParamDO actual) {
        assertBaseInfo(expected, actual);

        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(expected.getTaskId(), actual.getTaskId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

    private void asserts(TaskParamDO expected, TaskParam actual) {
        assertBaseInfo(expected, actual);

        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(expected.getTaskId(), actual.getTaskId());
        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

    @Test
    public void toTaskParam() {

        TaskParamDO expected = null;
        TaskParam actual = ModelConverter.toTaskParam(expected);
        Assert.assertNull(actual);

        expected = newTaskParamDO(1);
        actual = ModelConverter.toTaskParam(expected);
        asserts(expected, actual);
    }

    @Test
    public void toTaskParams() {


        List<TaskParamDO> expecteds = null;
        List<TaskParam> actuals = ModelConverter.toTaskParams(expecteds);
        Assert.assertNull(actuals);

        expecteds = new ArrayList<>(3);
        expecteds.add(newTaskParamDO(1));
        expecteds.add(newTaskParamDO(2));
        expecteds.add(null);
        actuals = ModelConverter.toTaskParams(expecteds);
        asserts(expecteds.get(0), actuals.get(0));
        asserts(expecteds.get(1), actuals.get(1));
    }

    private TaskParam newTaskParam(int index) {

        TaskParam result = new TaskParam();
        fillBaseInfo(result, TaskParamStatusEnum.NORMAL);
        result.setBizCode("bizCode" + index);
        result.setBizId("bizId" + index);
        result.setTaskId("taskId" + index);
        result.setValue("value" + index);

        return result;
    }

    private TaskLog newTaskLog(int index) {

        TaskLog result = new TaskLog();
        fillBaseInfo(result, TaskLogStatusEnum.SUCCESS);
        result.setBizCode("bizCode" + index);
        result.setBizId("bizId" + index);
        result.setTaskId("taskId" + index);
        result.setDescription("desc" + index);

        return result;
    }

    @Test
    public void toTaskParamDO() {

        TaskParam expected = null;
        TaskParamDO actual = ModelConverter.toTaskParamDO(expected);
        Assert.assertNull(actual);

        expected = newTaskParam(1);
        actual = ModelConverter.toTaskParamDO(expected);
        asserts(expected, actual);
    }

    private void asserts(TaskLog expected, TaskLogDO actual) {
        assertBaseInfo(expected, actual);

        Assert.assertEquals(expected.getBizCode(), actual.getBizCode());
        Assert.assertEquals(expected.getBizId(), actual.getBizId());
        Assert.assertEquals(expected.getTaskId(), actual.getTaskId());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void toTaskLogDO() {

        TaskLog expected = null;
        TaskLogDO actual = ModelConverter.toTaskLogDO(expected);
        Assert.assertNull(actual);

        expected = newTaskLog(1);
        actual = ModelConverter.toTaskLogDO(expected);
        asserts(expected, actual);
    }
}