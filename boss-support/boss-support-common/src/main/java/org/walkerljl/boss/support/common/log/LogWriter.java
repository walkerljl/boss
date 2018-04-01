package org.walkerljl.boss.support.common.log;


import org.walkerljl.toolkit.standard.Result;

/**
 * 日志输出者
 *
 * @author lijunlin
 */
public interface LogWriter {

    /**
     * 添加系统日志
     *
     * @param sysLogs 系统日志对象
     * @return
     */
    Result<Boolean> write(SysLog... sysLogs);
}
