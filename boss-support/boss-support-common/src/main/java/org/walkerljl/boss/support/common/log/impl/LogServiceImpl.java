package org.walkerljl.boss.support.common.log.impl;

import org.walkerljl.boss.support.common.log.LogService;
import org.walkerljl.boss.support.common.log.LogServiceException;
import org.walkerljl.boss.support.common.log.LogWriter;
import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志服务
 *
 * @author lijunlin
 */
public class LogServiceImpl implements LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);
    /**
     * 日志队列默认容量
     */
    private static final int DEFAULT_CAPACITY = 100;
    /**
     * 批量处理大小
     */
    private static final int BATCH_SIZE = 100;
    /**
     * 队列
     */
    private final BlockingQueue<SysLog> logQueue;
    /**
     * 消费者
     */
    private final LogConsumer           logConsumer;
    /**
     * 服务名称
     */
    private String                      name;
    /**
     * 是否已经启动
     */
    private volatile boolean            isStart;
    /**
     * 是否已经停止
     */
    private volatile boolean            isStop;
    /**
     * 当前队列剩余日志量
     */
    private volatile int                reservations;
    private volatile SysLog[]           sysLogBuffer;
    private volatile int sysLogBufferIndex = 0;
    private LogWriter logWriter;

    /**
     * 创建一个日志服务
     *
     * @param name   名称
     * @param writer 日志输出对象
     */
    public LogServiceImpl(String name, LogWriter writer) {
        this(name, writer, DEFAULT_CAPACITY);
    }

    /**
     * 创建一个日志服务
     *
     * @param name     名称
     * @param writer   日志输出对象
     * @param CAPACITY 日志队列容量
     */
    public LogServiceImpl(String name, LogWriter writer, int CAPACITY) {
        this.name = name;
        this.logQueue = new LinkedBlockingQueue<SysLog>(CAPACITY);
        this.logWriter = writer;
        this.logConsumer = new LogConsumer(writer);
        this.logConsumer.setName(name);
    }

    @Override
    public void start() {
        synchronized (this) {
            if (isStart) {
                LOGGER.warn(getFullServiceName() + "已经启动，请勿多次启动");
            } else {
                LOGGER.info(getFullServiceName() + "正在启动");
                //创建日志消费者
                logConsumer.start();
                //创建日志输出守护线程, 1分钟自动将日志缓冲队列中的日志输出
                new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        String messagePrefix = "守护线程输出入日志";
                        synchronized (this) {
                            if (sysLogBuffer == null || sysLogBuffer.length == 0) {
                                return;
                            }
                            //输出日志
                            long startTime = System.currentTimeMillis();
                            logWriter.write(sysLogBuffer);
                            sysLogBuffer = new SysLog[BATCH_SIZE];
                            sysLogBufferIndex = 0;
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug(String.format("%s, BATCH_SIZE=%s, 当前队列大小=%s, 共耗时%s毫秒",
                                        messagePrefix, BATCH_SIZE, (System.currentTimeMillis() - startTime)));
                            }
                        }
                    }
                }, 10, 1 * 60, TimeUnit.SECONDS);

                addShutdownHook();
                isStart = true;
                LOGGER.info(getFullServiceName() + "启动成功");
            }
        }
    }

    @Override
    public void stop() {
        LOGGER.info(getFullServiceName() + "正在停止日志服务");
        synchronized (this) {
            isStart = false;
            isStop = true;
        }
        logConsumer.interrupt();
        LOGGER.info(getFullServiceName() + "停止日志服务成功");
    }

    @Override
    public boolean isRunning() {
        return isStart && !isStop;
    }

    @Override
    public String getServiceName() {
        return this.name;
    }

    /**
     * 获取日志服务全名
     *
     * @return
     */
    private String getFullServiceName() {
        return "日志服务[" + this.name + "]";
    }

    @Override
    public void push(SysLog... sysLogs) {
        if (sysLogs == null || sysLogs.length == 0) {
            LOGGER.warn(getFullServiceName() + "日志包为NULL, 扔掉该日志包");
            return;
        }
        for (SysLog sysLog : sysLogs) {
            synchronized (this) {
                if (isStop) {
                    throw new IllegalStateException(getFullServiceName() + "日志服务已经停止, 无法继续接收日志包");
                }
                ++reservations;
            }
            try {
                logQueue.put(sysLog);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(getFullServiceName() + "成功Push一条日志:" + sysLog.toString());
                }
            } catch (Throwable e) {
                LOGGER.error(getFullServiceName() + "Push一条日志失败:" + sysLog.toString(), e);
            }
        }
    }

    /**
     * JVM关闭之前先停止日志服务
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    LOGGER.info(getFullServiceName() + "JVM即将关闭, 日志服务将停止, 不再接受日志包");
                    LogServiceImpl.this.stop();
                } catch (Exception e) {
                    LOGGER.error(getFullServiceName() + "无法停止日志服务");
                }
            }
        });
    }

    /**
     * 日志处理线程 消费者
     *
     * @author lijunlin
     */
    private class LogConsumer extends Thread {
        /**
         * 日志输出对象
         */
        private final LogWriter writer;

        /**
         * 创建一个日志对象
         *
         * @param writer 日志输出对象
         */
        public LogConsumer(LogWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            String messagePrefix = "输出日志";
            while (true) {
                try {
                    synchronized (this) {
                        if (isStop && reservations == 0) {
                            LOGGER.warn(getFullServiceName() + "日志输出线程正常退出...");
                            break;
                        }
                    }
                    SysLog sysLog = logQueue.take();
                    synchronized (this) {
                        --reservations;
                    }
                    synchronized (this) {
                        //将日志放入批量输出列表
                        sysLogBuffer[sysLogBufferIndex] = sysLog;
                        sysLogBufferIndex++;
                        if (sysLogBufferIndex != BATCH_SIZE) {
                            continue;
                        }

                        //输出日志
                        long startTime = System.currentTimeMillis();
                        writer.write(sysLogBuffer);
                        sysLogBuffer = new SysLog[BATCH_SIZE];
                        sysLogBufferIndex = 0;
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(String.format("%s, BATCH_SIZE=%s, 当前队列大小=%s, 共耗时%s毫秒",
                                    messagePrefix, BATCH_SIZE, (System.currentTimeMillis() - startTime)));
                        }
                    }
                } catch (LogServiceException e) {
                    LOGGER.error(getFullServiceName() + messagePrefix + "失败");
                } catch (InterruptedException e) {
                    LOGGER.error(getFullServiceName() + "输出日志线程被中断");
                } catch (Throwable e) {
                    LOGGER.error(messagePrefix + "失败", e);
                }
            }
        }
    }
}