package org.walkerljl.boss.service.exclude.task;

import org.junit.Test;
import org.walkerljl.boss.service.base.BaseIntegrationTest;

/**
 * TaskTest
 *
 * @author xingxun
 */
public class TaskTest extends BaseIntegrationTest {

    @Test
    public void start() {

        synchronized (TaskTest.class) {
            while (true) {
                try {
                    TaskTest.class.wait();
                } catch (InterruptedException ex) {}
            }
        }
    }
}