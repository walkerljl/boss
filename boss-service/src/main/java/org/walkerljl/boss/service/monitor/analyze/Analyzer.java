package org.walkerljl.boss.service.monitor.analyze;

import org.walkerljl.boss.service.task.TaskHandler;

/**
 * 分析器
 *
 * @author xingxun
 */
public interface Analyzer extends TaskHandler {

    /**
     * 分析
     *
     * @param context 上下文
     */
    void analyze(AnalyzeContext context);
}