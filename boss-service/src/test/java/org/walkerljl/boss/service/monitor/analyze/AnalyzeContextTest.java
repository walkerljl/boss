package org.walkerljl.boss.service.monitor.analyze;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * AnalyzeContextTest
 *
 * @author xingxun
 */
public class AnalyzeContextTest extends BaseMonitorUnitTest {

    private static final String CONFIG = "context.config";
    private static final String MONITOR_DATA = "context.monitordata";

    @Test
    public void test() {
        Assert.assertEquals(CONFIG, AnalyzeContext.CONFIG);
        Assert.assertEquals(MONITOR_DATA, AnalyzeContext.MONITOR_DATA);

    }

}