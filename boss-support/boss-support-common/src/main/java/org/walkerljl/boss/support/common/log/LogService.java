package org.walkerljl.boss.support.common.log;

/**
 * 日志服务
 *
 * @author xingxun
 */
public interface LogService {

    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 是否正在运行
     *
     * @return
     */
    boolean isRunning();

    /**
     * Push日志
     *
     * @param sysLogs
     */
    void push(SysLog... sysLogs);

    /**
     * 获取服务名
     *
     * @return
     */
    String getServiceName();
}