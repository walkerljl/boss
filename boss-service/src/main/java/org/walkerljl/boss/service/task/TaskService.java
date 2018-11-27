package org.walkerljl.boss.service.task;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskLog;
import org.walkerljl.boss.service.task.model.TaskParam;

/**
 * 通用任务业务接口
 *
 * @author xingxun
 */
public interface TaskService {

    /**
     * 添加任务执行日志
     *
     * @param taskLog 任务执行日志
     */
    void saveTaskLog(TaskLog taskLog);

    /**
     * 锁任务
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     * @param timeout 超时时间
     * @return
     */
    boolean lockTask(String bizCode, String bizId, String taskId, Long timeout);

    /**
     * 标注任务执行成功
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     */
    void markTaskExecutedSuccess(String bizCode, String bizId, String taskId);

    /**
     * 标注任务执行失败
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     * @param nextRetryTime 下次重试时间
     * @param errorMsg 失败信息
     */
    void markTaskExecutedFailure(String bizCode, String bizId, String taskId, Date nextRetryTime, String errorMsg);

    /**
     * 根据业务编码、业务ID、任务ID查询任务
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param  id 任务ID
     */
    Task getTask(String bizCode, String bizId, String id);

    /**
     * 根据业务编码、业务ID、任务ID查询任务执行参数列表
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 任务ID
     * @return
     */
    List<TaskParam> listTaskParams(String bizCode, String bizId, String taskId);

    /**
     * 分页查询未处理的任务列表
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<Task> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询处理失败的任务列表
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<Task> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询超时的任务列表
     *
     * @param loadInterval 加载间隔
     * @param retryTimeout 重试超时时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<Task> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize);
}