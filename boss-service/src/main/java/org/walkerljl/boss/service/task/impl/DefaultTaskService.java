package org.walkerljl.boss.service.task.impl;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.dao.daointerface.task.TaskDAO;
import org.walkerljl.boss.dao.daointerface.task.TaskLogDAO;
import org.walkerljl.boss.dao.daointerface.task.TaskParamDAO;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.exception.TaskSalException;
import org.walkerljl.boss.service.task.impl.util.ModelConverter;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * DefaultTaskService
 *
 * @author xingxun
 */
public class DefaultTaskService implements TaskService {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.SAL_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.SAL_DETAIL);

    private TaskDAO      taskDAO;
    private TaskParamDAO taskParamDAO;
    private TaskLogDAO   taskLogDAO;

    @Override
    public void saveTaskLog(TaskLog taskLog) {
        InvocationInfo<TaskLog, Void> invocationInfo =
                new InvocationInfo<>(getClass(), "saveTaskLog", taskLog);

        try {
            taskLogDAO.save(ModelConverter.toTaskLogDO(taskLog));
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public boolean lockTask(String bizCode, String bizId, String taskId, Long timeout) {
        InvocationInfo<Object[], Boolean> invocationInfo =
                new InvocationInfo<>(getClass(), "lockTask", new Object[] {bizCode, bizId, taskId, timeout});

        try {
            int counter = taskDAO.lock(bizCode, bizId, Long.parseLong(taskId), timeout);
            boolean isLocked = counter > 0;
            invocationInfo.markSuccess(isLocked);
            return isLocked;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void markTaskExecutedSuccess(String bizCode, String bizId, String taskId) {
        InvocationInfo<Object[], Boolean> invocationInfo =
                new InvocationInfo<>(getClass(), "markTaskExecutedSuccess", new Object[] {bizCode, bizId, taskId});

        try {
            taskDAO.markExecutedSuccess(bizCode, bizId, Long.parseLong(taskId));
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public void markTaskExecutedFailure(String bizCode, String bizId, String taskId, Date nextRetryTime, String errorMsg) {
        InvocationInfo<Object[], Void> invocationInfo =
                new InvocationInfo<>(getClass(), "markTaskExecutedFailure", new Object[] {bizCode, bizId, taskId,
                        nextRetryTime, errorMsg});

        try {
            taskDAO.markExecutedFailure(bizCode, bizId, Long.parseLong(taskId), nextRetryTime, errorMsg);
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public Task getTask(String bizCode, String bizId, String taskId) {
        InvocationInfo<Object[], Task> invocationInfo =
                new InvocationInfo<>(getClass(), "getTask", new Object[] {bizCode, bizId, taskId});

        try {
            Task resultData = ModelConverter.toTask(taskDAO.get(bizCode, bizId, Long.parseLong(taskId)));
            invocationInfo.markSuccess(resultData);
            return resultData;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<TaskParam> listTaskParams(String bizCode, String bizId, String taskId) {
        InvocationInfo<Object[], List<TaskParam>> invocationInfo =
                new InvocationInfo<>(getClass(), "listTaskParams", new Object[] {bizCode, bizId, taskId});

        try {
            List<TaskParam> resultDataList = ModelConverter.toTaskParams(taskParamDAO.list(bizCode, bizId, Long.parseLong(taskId)));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<Task> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        InvocationInfo<Object[], List<Task>> invocationInfo =
                new InvocationInfo<>(getClass(), "listUnprocessTasks", new Object[] {loadInterval, currentPage, pageSize});

        try {
            List<Task> resultDataList = ModelConverter.toTasks(
                    taskDAO.listUnprocessTasks(loadInterval, currentPage, pageSize));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<Task> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize) {
        InvocationInfo<Object[], List<Task>> invocationInfo =
                new InvocationInfo<>(getClass(), "listFailureTasks", new Object[] {loadInterval, currentPage, pageSize});

        try {
            List<Task> resultDataList = ModelConverter.toTasks(
                    taskDAO.listFailureTasks(loadInterval, currentPage, pageSize));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    @Override
    public List<Task> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize) {
        InvocationInfo<Object[], List<Task>> invocationInfo =
                new InvocationInfo<>(getClass(), "listTimeoutTasks", new Object[] {loadInterval, retryTimeout, currentPage, pageSize});

        try {
            List<Task> resultDataList = ModelConverter.toTasks(taskDAO.listTimeoutTasks(loadInterval, retryTimeout,
                    currentPage, pageSize));
            invocationInfo.markSuccess(resultDataList);
            return resultDataList;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSalException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    /**
     * Setter method for property <tt>taskDAO</tt>.
     *
     * @param taskDAO  value to be assigned to property taskDAO
     */
    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    /**
     * Setter method for property <tt>taskParamDAO</tt>.
     *
     * @param taskParamDAO  value to be assigned to property taskParamDAO
     */
    public void setTaskParamDAO(TaskParamDAO taskParamDAO) {
        this.taskParamDAO = taskParamDAO;
    }

    /**
     * Setter method for property <tt>taskLogDAO</tt>.
     *
     * @param taskLogDAO  value to be assigned to property taskLogDAO
     */
    public void setTaskLogDAO(TaskLogDAO taskLogDAO) {
        this.taskLogDAO = taskLogDAO;
    }
}