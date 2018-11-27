package org.walkerljl.boss.service.task.shell.dispatch.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchSplitor;

/**
 * DefaultTaskDispatchSplitorTest
 *
 * @author xingxun
 */
public class DefaultTaskDispatchSplitorTest extends BaseTaskUnitTest {

    @Test
    public void split() {

        TaskService taskService = new DummyTaskService();
        TaskExecutionConfig executionConfig = new TaskExecutionConfig();
        TaskDispatchSplitor taskDispatchSplitor = new DefaultTaskDispatchSplitor(taskService, executionConfig);
        taskDispatchSplitor.split();
    }
}

class DummyTaskService implements TaskService {

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
        int capacity = pageSize;
        List<Task> tasks = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tasks.add(null);
        }
        return tasks;
    }

    @Override
    public List<Task> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        int capacity = pageSize + 1;
        List<Task> tasks = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tasks.add(null);
        }
        return tasks;
    }

    @Override
    public List<Task> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
        int capacity = pageSize - 1;
        List<Task> tasks = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tasks.add(null);
        }
        return tasks;
    }
}