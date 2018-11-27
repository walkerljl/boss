package org.walkerljl.boss.service.task.impl.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.DummyDataSource;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.exception.TaskException;
import org.walkerljl.boss.service.task.impl.RunnableTask;
import org.walkerljl.boss.service.task.impl.TaskExecutionContext;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.model.enums.status.TaskStatusEnum;

/**
 * DefaultRunnableTaskTest
 *
 * @author xingxun
 */
public class DefaultRunnableTaskTest extends BaseTaskUnitTest {

    @Test(expected = TaskException.class)
    public void runForUnlock() {

        TaskExecutionContext context = new TaskExecutionContext();
        context.setAttribute(TaskExecutionContext.TASK_BIZ_CODE, "bizCode");
        context.setAttribute(TaskExecutionContext.TASK_BIZ_ID, "bizId");
        context.setAttribute(TaskExecutionContext.TASK_ID,"taskId");
        context.setAttribute(TaskExecutionContext.TASK_TIME_OUT,1L);

        TaskService taskService = new TaskService() {
            @Override
            public void saveTaskLog(TaskLog taskLog) {

            }

            @Override
            public boolean lockTask(String bizCode, String bizId, String taskId, Long timeout) {
                return false;
            }

            @Override
            public void markTaskExecutedSuccess(String bizCode, String bizId, String taskId) {

            }

            @Override
            public void markTaskExecutedFailure(String bizCode, String bizId, String taskId, Date nextRetryTime, String errorMsg) {

            }

            @Override
            public Task getTask(String bizCode, String bizId, String id) {
                return null;
            }

            @Override
            public List<TaskParam> listTaskParams(String bizCode, String bizId, String taskId) {
                return null;
            }

            @Override
            public List<Task> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
                return null;
            }

            @Override
            public List<Task> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
                return null;
            }

            @Override
            public List<Task> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
                return null;
            }
        };

        RunnableTask runnableTask = new DefaultRunnableTask(context, taskService, new DummyDataSource());
        runnableTask.run();
    }

    @Test
    public void run() {

        TaskExecutionContext context = new TaskExecutionContext();
        context.setAttribute(TaskExecutionContext.TASK_BIZ_CODE, "bizCode");
        context.setAttribute(TaskExecutionContext.TASK_BIZ_ID, "bizId");
        context.setAttribute(TaskExecutionContext.TASK_ID,"taskId");
        context.setAttribute(TaskExecutionContext.TASK_TIME_OUT,1L);

        TaskService taskService = new TaskService() {
            @Override
            public void saveTaskLog(TaskLog taskLog) {

            }

            @Override
            public boolean lockTask(String bizCode, String bizId, String taskId, Long timeout) {
                return true;
            }

            @Override
            public void markTaskExecutedSuccess(String bizCode, String bizId, String taskId) {

            }

            @Override
            public void markTaskExecutedFailure(String bizCode, String bizId, String taskId, Date nextRetryTime, String errorMsg) {

            }

            @Override
            public Task getTask(String bizCode, String bizId, String id) {
                Task task = new Task();
                task.setAttempts(1);
                task.setMaxAttempts(10);
                task.setStatus(TaskStatusEnum.UNPROCESS);
                return task;
            }

            @Override
            public List<TaskParam> listTaskParams(String bizCode, String bizId, String taskId) {
                return null;
            }

            @Override
            public List<Task> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
                return null;
            }

            @Override
            public List<Task> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
                return null;
            }

            @Override
            public List<Task> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
                return null;
            }
        };

        RunnableTask runnableTask = new DefaultRunnableTask(context, taskService, new DummyDataSource());
        runnableTask.run();
    }
}