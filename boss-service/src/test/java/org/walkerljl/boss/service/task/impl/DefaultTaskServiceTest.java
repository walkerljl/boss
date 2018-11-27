package org.walkerljl.boss.service.task.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.walkerljl.boss.dao.daointerface.task.TaskDAO;
import org.walkerljl.boss.dao.daointerface.task.TaskLogDAO;
import org.walkerljl.boss.dao.daointerface.task.TaskParamDAO;
import org.walkerljl.boss.dao.dataobject.task.TaskDO;
import org.walkerljl.boss.dao.dataobject.task.TaskLogDO;
import org.walkerljl.boss.dao.dataobject.task.TaskParamDO;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.exception.TaskSalException;
import org.walkerljl.boss.service.task.model.TaskLog;

/**
 * DefaultTaskServiceTest
 *
 * @author xingxun
 */
public class DefaultTaskServiceTest extends BaseTaskUnitTest {

    @Mock
    private TaskDAO      taskDAO;
    @Mock
    private TaskParamDAO taskParamDAO;
    @Mock
    private TaskLogDAO   taskLogDAO;
    @InjectMocks
    private TaskService taskService = new DefaultTaskService();

    @Test(expected = TaskSalException.class)
    public void saveTaskLog() {
        TaskLog taskLog = null;
        taskService.saveTaskLog(taskLog);

        taskLog = new TaskLog();
        taskService.saveTaskLog(taskLog);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskLogDAO(new TaskLogDAO() {
            @Override
            public void save(TaskLogDO taskLog) {
                throw new RuntimeException();
            }
        });
        taskService.saveTaskLog(new TaskLog());
    }

    @Test(expected = TaskSalException.class)
    public void lockTask() {
        String bizCode = "bizCode";
        String bizId = "bizId";
        String taskId = "taskId";
        Long timeout = 1L;

        taskService.lockTask(bizCode, bizId, taskId, timeout);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.lockTask(bizCode, bizId, taskId, timeout);
    }

    @Test(expected = TaskSalException.class)
    public void markTaskExecutedSuccess() {
        String bizCode = "bizCode";
        String bizId = "bizId";
        String taskId = "taskId";
        taskService.markTaskExecutedSuccess(bizCode, bizId, taskId);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.markTaskExecutedSuccess(bizCode, bizId, taskId);
    }

    @Test(expected = TaskSalException.class)
    public void markTaskExecutedFailure() {

        String bizCode = "bizCode";
        String bizId = "bizId";
        String taskId = "taskId";
        Date nextRetryTime = new Date();
        String errorMsg = "errorMsg";
        taskService.markTaskExecutedFailure(bizCode, bizId, taskId, nextRetryTime, errorMsg);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.markTaskExecutedFailure(bizCode, bizId, taskId, nextRetryTime, errorMsg);
    }

    @Test(expected = TaskSalException.class)
    public void listTaskParams() {
        String bizCode = "bizCode";
        String bizId = "bizId";
        String taskId = "taskId";
        taskService.listTaskParams(bizCode, bizId, taskId);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskParamDAO(new ExceptionTaskParamDAO());
        taskService.listTaskParams(bizCode, bizId, taskId);
    }

    @Test(expected = TaskSalException.class)
    public void listUnprocessTasks() {
        Long loadInterval = 1L;
        Integer currentPage = 1;
        Integer pageSize = 2;
        taskService.listUnprocessTasks(loadInterval, currentPage, pageSize);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.listUnprocessTasks(loadInterval, currentPage, pageSize);
    }

    @Test(expected = TaskSalException.class)
    public void listFailureTasks() {
        Long loadInterval = 1L;
        Integer currentPage = 1;
        Integer pageSize = 2;
        taskService.listFailureTasks(loadInterval, currentPage, pageSize);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.listFailureTasks(loadInterval, currentPage, pageSize);
    }

    @Test(expected = TaskSalException.class)
    public void listTimeoutTasks() {
        Long retryTimeout = 1L;
        Long loadInterval = 1L;
        Integer currentPage = 1;
        Integer pageSize = 2;
        taskService.listTimeoutTasks(loadInterval, retryTimeout, currentPage, pageSize);

        DefaultTaskService taskService = new DefaultTaskService();
        taskService.setTaskDAO(new ExceptionTaskDAO());
        taskService.listTimeoutTasks(loadInterval, retryTimeout, currentPage, pageSize);
    }
}

class ExceptionTaskDAO implements TaskDAO {

    @Override
    public void save(TaskDO task) {

    }

    @Override
    public int lock(String bizCode, String bizId, Long id, Long timeout) {
        throw new RuntimeException();
    }

    @Override
    public void markExecutedSuccess(String bizCode, String bizId, Long id) {
        throw new RuntimeException();
    }

    @Override
    public void markExecutedFailure(String bizCode, String bizId, Long id, Date nextRetryTime, String errorMsg) {
        throw new RuntimeException();
    }

    @Override
    public void updateStatus(String bizCode, String bizId, Long id, String status) {

    }

    @Override
    public TaskDO get(String bizCode, String bizId, Long id) {
        throw new RuntimeException();
    }

    @Override
    public List<TaskDO> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        throw new RuntimeException();
    }

    @Override
    public List<TaskDO> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        throw new RuntimeException();
    }

    @Override
    public List<TaskDO> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
        throw new RuntimeException();
    }
}

class ExceptionTaskLogDAO implements TaskLogDAO {

    @Override
    public void save(TaskLogDO taskLog) {
        throw new RuntimeException();
    }
}

class ExceptionTaskParamDAO implements TaskParamDAO {

    @Override
    public void save(TaskParamDO taskParam) {
        throw new RuntimeException();
    }

    @Override
    public List<TaskParamDO> list(String bizCode, String bizId, Long taskId) {
        throw new RuntimeException();
    }
}