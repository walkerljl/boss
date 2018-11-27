package org.walkerljl.boss.service.task.impl.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.walkerljl.boss.service.task.impl.executor.impl.DefaultTaskExecutor;

/**
 * 任务执行器仓库
 *
 * @author xingxun
 */
public class TaskExecutorRepository {

    private final Map<String, TaskExecutor> REPOSITROY = new HashMap<>(3);

    private final ReadWriteLock lock      = new ReentrantReadWriteLock();
    private final Lock          readLock  = lock.readLock();
    private final Lock          writeLock = lock.writeLock();

    /**
     * 私有构造函数
     */
    private TaskExecutorRepository() {}

    /**
     * 获取实例
     *
     * @return
     */
    public static TaskExecutorRepository getInstance() {
        return Holder.instance;
    }

    /**
     * 查找执行器
     *
     * @param config 配置
     * @return
     */
    public TaskExecutor lookup(TaskExecutorConfig config) {
        if (config == null) {
            return null;
        }
        TaskExecutor executor = null;

        readLock.lock();
        try {
            executor = REPOSITROY.get(config.getId());
            if (executor == null) {
                readLock.unlock();
                writeLock.lock();
                try {
                    executor = REPOSITROY.get(config.getId());
                    if (executor == null) {
                        executor = new DefaultTaskExecutor(config);
                        if (executor != null) {
                            executor.start();
                            REPOSITROY.put(config.getId(), executor);
                        }
                    }
                } finally {
                    writeLock.unlock();
                    readLock.lock();
                }
            }
        } finally {
            readLock.unlock();
        }
        return executor;
    }

    /**
     * 单列容器
     */
    private static class Holder {
        private static TaskExecutorRepository instance = new TaskExecutorRepository();
    }
}