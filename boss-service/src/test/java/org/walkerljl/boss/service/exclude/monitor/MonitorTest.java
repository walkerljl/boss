package org.walkerljl.boss.service.exclude.monitor;

import org.junit.Test;
import org.walkerljl.boss.service.base.BaseIntegrationTest;

/**
 * MonitorTest
 *
 * @author xingxun
 */
public class MonitorTest extends BaseIntegrationTest {

    @Test
    public void start() {

        synchronized (MonitorTest.class) {
            while (true) {
                try {
                    MonitorTest.class.wait();
                } catch (InterruptedException ex) {}
            }
        }
    }
}