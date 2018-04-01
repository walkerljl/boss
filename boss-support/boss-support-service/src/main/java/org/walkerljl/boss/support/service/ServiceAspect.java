package org.walkerljl.boss.support.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.walkerljl.boss.support.common.log.LogService;
import org.walkerljl.boss.support.common.log.LogWriter;
import org.walkerljl.boss.support.common.log.SysLog;
import org.walkerljl.boss.support.common.log.impl.LogServiceImpl;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.exception.AppServiceException;

/**
 * 业务逻辑层切面
 *
 * @author lijunlin
 */
public class ServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
    /**
     * 日志队列大小，默认为1000
     */
    private int logQueueSize = 10000;
    /**
     * 日志服务
     */
    private LogService logService;
    /**
     * 日志输出器
     */
    private LogWriter  logWriter;
    /**
     * 日志服务名称
     */
    private String     logServiceName;

    /**
     * 初始化
     */
    public void init() {
        logService = new LogServiceImpl(logServiceName, logWriter, logQueueSize);
        logService.start();
    }

    /**
     * 环绕增强
     */
    public Object doAspect(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object returnValue = null;
        boolean invokedResult = true;
        Object[] args = joinPoint.getArgs();
        try {
            returnValue = joinPoint.proceed(args);
        } catch (Throwable e) {
            invokedResult = false;
            throw new AppServiceException(e.getMessage(), e);
        } finally {
            long cousumeTime = System.currentTimeMillis() - startTime;
            processSysLog(joinPoint.getTarget(), args, cousumeTime, invokedResult);

            //DEBUG
            if (LOGGER.isDebugEnabled()) {
                String clazzName = joinPoint.getTarget().getClass().getName();
                String invokedMethodName = joinPoint.getSignature().getName();
                String methodName = clazzName + "." + invokedMethodName;
                LOGGER.debug(String.format("此次调用共耗时:%s毫秒, 结果:%s, 方法:%s.",
                        new Object[]{cousumeTime, (invokedResult ? "成功" : "失败"), methodName}));
            }
        }
        return returnValue;
    }

    /**
     * 处理系统日志
     *
     * @param target
     * @param args
     * @param consumeTime
     * @param invokedResult
     */
    private void processSysLog(final Object target, final Object[] args, final long consumeTime, boolean invokedResult) {
        if (target == null || args == null || args.length == 0 || (target instanceof LogWriter)) {
            return;
        }
        for (Object arg : args) {
            if (arg == null || !(arg instanceof SysLog)) {
                continue;
            }
            SysLog sysLog = (SysLog) arg;
            logService.push(sysLog);
        }
    }

    /**
     * 设置日志队列大小
     *
     * @param logQueueSize
     */
    public void setLogQueueSize(int logQueueSize) {
        this.logQueueSize = logQueueSize;
    }

    /**
     * 设置日志输出器
     *
     * @param logWriter
     */
    public void setLogWriter(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    /**
     * 设置日志服务名称
     *
     * @param logServiceName
     */
    public void setLogServiceName(String logServiceName) {
        this.logServiceName = logServiceName;
    }
}