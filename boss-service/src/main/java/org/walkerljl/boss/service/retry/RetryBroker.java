package org.walkerljl.boss.service.retry;

import org.walkerljl.boss.service.retry.model.RetryTask;
import org.walkerljl.toolkit.standard.machine.Machine;

/**
 * Retry broker
 *
 * @author xingxun
 */
public interface RetryBroker extends Machine {

    /**
     * 提交一个重试任务
     *
     * @param retryTask 重试任务
     * @return 重试任务ID
     */
    String submit(RetryTask retryTask);

    /**
     * 标注重试任务已经完成
     *
     * @param bizCode 业务编码
     * @param bizId 业务ID
     * @param taskId 重试任务ID
     */
    void markRetryJobToCompleted(String bizCode, String bizId, String taskId);
}