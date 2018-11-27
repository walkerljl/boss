package org.walkerljl.boss.dao.daointerface.task;

import java.util.Date;
import java.util.List;

import org.walkerljl.boss.dao.dataobject.task.TaskDO;

/**
 * 任务数据访问层接口
 *
 * @author xingxun
 */
public interface TaskDAO {

    /**
     * 添加任务
     *
     * @param task 任务
     */
    void save(TaskDO task);

    /**
     * 锁任务
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param id 任务ID
     * @param timeout 超时时间
     * @return
     */
    int lock(String bizCode, String bizId, Long id, Long timeout);

    /**
     * 标注任务执行成功
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param id 任务ID
     */
    void markExecutedSuccess(String bizCode, String bizId, Long id);

    /**
     * 标注任务执行失败
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param id 任务ID
     * @param nextRetryTime 下次重试时间
     * @param errorMsg 失败信息
     */
    void markExecutedFailure(String bizCode, String bizId, Long id, Date nextRetryTime, String errorMsg);

    /**
     * 更新任务状态
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param id 任务ID
     * @param status 任务状态
     */
    void updateStatus(String bizCode, String bizId, Long id, String status);

    /**
     * 根据业务编码、业务ID、任务ID查询任务
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param  id 任务ID
     */
    TaskDO get(String bizCode, String bizId, Long id);

    /**
     * 分页查询未处理的任务列表
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<TaskDO> listUnprocessTasks(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询处理失败的任务列表
     *
     * @param loadInterval 加载间隔
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<TaskDO> listFailureTasks(Long loadInterval, Integer currentPage, Integer pageSize);

    /**
     * 分页查询超时的任务列表
     *
     * @param loadInterval 加载间隔
     * @param retryTimeout 重试超时时间
     * @param currentPage 当前页码
     * @param pageSize 分页大小
     * @return
     */
    List<TaskDO> listTimeoutTasks(Long loadInterval, Long retryTimeout, Integer currentPage, Integer pageSize);
}