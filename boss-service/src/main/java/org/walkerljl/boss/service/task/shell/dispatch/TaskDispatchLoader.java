package org.walkerljl.boss.service.task.shell.dispatch;

import java.util.List;

/**
 * 监控任务Loader
 *
 * @author xingxun
 */
public interface TaskDispatchLoader {

    /**
     * 任务加载
     *
     * @param splitedTaskItem 拆分的任务项
     * @return
     */
    List<String> load(SplitedTaskItem splitedTaskItem);
}