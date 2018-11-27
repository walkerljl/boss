package org.walkerljl.boss.service.task.shell.dispatch;

import java.util.List;

/**
 * 监控任务Splitor
 *
 * @author xingxun
 */
public interface TaskDispatchSplitor {

    /**
     * 任务拆分
     *
     * @return
     */
    List<SplitedTaskItem> split();
}