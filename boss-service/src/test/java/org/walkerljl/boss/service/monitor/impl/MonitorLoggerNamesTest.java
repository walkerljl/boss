package org.walkerljl.boss.service.monitor.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * MonitorLoggerNamesTest
 *
 * @author xingxun
 */
public class MonitorLoggerNamesTest extends BaseMonitorUnitTest {

    /** 服务接入*/
    private static final String SAL_DIGEST = "MONITOR_SAL_DIGEST";
    private static final String SAL_DETAIL = "MONITOR_SAL_DETAIL";

    /** 分析*/
    private static final String ANALYZE_DIGEST = "MONITOR_ANALYZE_DIGEST";
    private static final String ANALYZE_DETAIL = "MONITOR_ANALYZE_DETAIL";

    /** 通知*/
    private static final String NOTIFIER_DIGEST = "MONITOR_NOTIFIER_DIGEST";
    private static final String NOTIFIER_DETAIL = "MONITOR_NOTIFIER_DETAIL";

    /** 预警*/
    private static final String ALARMER_DIGEST = "MONITOR_ALARMER_DIGEST";
    private static final String ALARMER_DETAIL = "MONITOR_ALARMER_DETAIL";

    @Test
    public void test() {

        Assert.assertEquals(SAL_DIGEST, MonitorLoggerNames.SAL_DIGEST);
        Assert.assertEquals(SAL_DETAIL, MonitorLoggerNames.SAL_DETAIL);

        Assert.assertEquals(ANALYZE_DIGEST, MonitorLoggerNames.ANALYZE_DIGEST);
        Assert.assertEquals(ANALYZE_DETAIL, MonitorLoggerNames.ANALYZE_DETAIL);

        Assert.assertEquals(NOTIFIER_DIGEST, MonitorLoggerNames.NOTIFIER_DIGEST);
        Assert.assertEquals(NOTIFIER_DETAIL, MonitorLoggerNames.NOTIFIER_DETAIL);

        Assert.assertEquals(ALARMER_DIGEST, MonitorLoggerNames.ALARMER_DIGEST);
        Assert.assertEquals(ALARMER_DETAIL, MonitorLoggerNames.ALARMER_DETAIL);
    }
}