package org.walkerljl.boss.service.task.shell.dispatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.task.TaskService;
import org.walkerljl.boss.service.task.impl.TaskExecutionConfig;
import org.walkerljl.boss.service.task.impl.TaskLoggerNames;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchSplitor;
import org.walkerljl.boss.service.task.shell.dispatch.exception.TaskSplitException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;
import org.walkerljl.toolkit.template.log.util.LoggerDetailUtil;
import org.walkerljl.toolkit.template.log.util.LoggerDigestUtil;

/**
 * DefaultTaskDispatchSplitor
 *
 * @author xingxun
 */
public class DefaultTaskDispatchSplitor implements TaskDispatchSplitor {

    private static final Logger DIGEST_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_SPLIT_DIGEST);
    private static final Logger DETAIL_LOGGER = LoggerFactory.getLogger(TaskLoggerNames.TASK_DISPATCH_SPLIT_DETAIL);

    private TaskService         taskService;
    private TaskExecutionConfig executionConfig;

    public DefaultTaskDispatchSplitor(TaskService taskService, TaskExecutionConfig executionConfig) {
        this.taskService = taskService;
        this.executionConfig = executionConfig;
    }

    @Override
    public List<SplitedTaskItem> split() {
        InvocationInfo<String, List<SplitedTaskItem>> invocationInfo =
                new InvocationInfo<>(getClass(), "split", null);

        try {
            List<Task> tasks = new ArrayList<>();
            AtomicInteger loadCounter = new AtomicInteger(0);
            //查询未处理的任务
            List<Task> partTasks = load0(LoadTypeEnum.UNPROCESS, loadCounter, 1,
                    executionConfig.getTaskLoadPageSize());
            CollectionUtil.addAll(tasks, partTasks);

            //查询执行失败的任务
            partTasks = load0(LoadTypeEnum.FAILURE, loadCounter, 1,
                    executionConfig.getTaskLoadPageSize());
            CollectionUtil.addAll(tasks, partTasks);

            //查询执行超时的任务
            partTasks = load0(LoadTypeEnum.TIMEOUNT, loadCounter, 1,
                    executionConfig.getTaskLoadPageSize());
            CollectionUtil.addAll(tasks, partTasks);

            List<SplitedTaskItem> splitTaskItems = new ArrayList<>();
            for (Task task : tasks) {
                if (task == null || task.getStatus() == null) {
                    continue;
                }
                SplitedTaskItem splitTaskItem = new DefaultSplitedTaskItem(
                        task.getBizCode(),
                        task.getBizId(),
                        task.getId(),
                        task.getStatus().getCode());
                splitTaskItems.add(splitTaskItem);
            }

            invocationInfo.markSuccess(splitTaskItems);

            return splitTaskItems;
        } catch (Throwable e) {
            invocationInfo.markFailure(e);
            throw new TaskSplitException(e);
        } finally {
            LoggerDigestUtil.logDigest(invocationInfo, DIGEST_LOGGER);
            LoggerDetailUtil.logDetail(invocationInfo, DETAIL_LOGGER);
        }
    }

    private List<Task> load0(LoadTypeEnum loadType, AtomicInteger loadCounter, int currentPage, int pageSize) {
        List<Task> tasks = null;
        if (loadType == LoadTypeEnum.UNPROCESS) {
            tasks = taskService.listUnprocessTasks(
                    executionConfig.getTaskLoadInterval(),
                    currentPage,
                    pageSize);
        } else if (loadType == LoadTypeEnum.FAILURE) {
            tasks = taskService.listFailureTasks(
                    executionConfig.getTaskLoadInterval(),
                    currentPage,
                    pageSize);
        } else if (loadType == LoadTypeEnum.TIMEOUNT) {
            tasks = taskService.listTimeoutTasks(
                    executionConfig.getTaskExecutionTimeout(),
                    executionConfig.getTaskLoadInterval(),
                    currentPage,
                    pageSize);
        }

        //调整计数器
        int partMonitorDatasSize = CollectionUtil.size(tasks);
        loadCounter.addAndGet(partMonitorDatasSize);

        boolean isContinueLoad = (partMonitorDatasSize == pageSize
                && (loadCounter.get() + pageSize) <= executionConfig.getMaxTaskQuantityPerLoad());

        if (!isContinueLoad) {
            return tasks;
        }

        return load0(loadType, loadCounter, currentPage + 1, pageSize);
    }
}

/**
 * 加载类型
 */
enum LoadTypeEnum {

    /**
     * 未处理
     */
    UNPROCESS,

    /**
     * 处理失败
     */
    FAILURE,

    /**
     * 处理超时
     */
    TIMEOUNT,;
}