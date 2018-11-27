package org.walkerljl.boss.service.monitor.analyze;

import org.walkerljl.boss.support.common.attribute.AttributeAccessor;
import org.walkerljl.boss.support.common.attribute.impl.DefaultAttributeAccessor;

/**
 * 分析上下文
 *
 * @author xingxun
 */
public class AnalyzeContext extends DefaultAttributeAccessor implements AttributeAccessor {

    private static final long serialVersionUID = 8979961856356080779L;

    public static final String TASK_CONTEXT = "context.task.context";
    public static final String CONFIG = "context.config";
    public static final String MONITOR_DATA = "context.monitordata";
}