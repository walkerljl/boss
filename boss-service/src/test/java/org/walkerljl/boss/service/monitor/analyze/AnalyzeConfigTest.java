package org.walkerljl.boss.service.monitor.analyze;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.monitor.BaseMonitorUnitTest;

/**
 * AnalyzeConfigTest
 *
 * @author xingxun
 */
public class AnalyzeConfigTest extends BaseMonitorUnitTest {

    @Test
    public void test() {
        AnalyzeConfig actual = new AnalyzeConfig();
        Assert.assertEquals("system", actual.getOperatorId());
    }
}