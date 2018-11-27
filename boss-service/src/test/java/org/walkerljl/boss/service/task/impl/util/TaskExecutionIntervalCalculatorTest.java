package org.walkerljl.boss.service.task.impl.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.support.common.util.DateUtil;

/**
 * TaskExecutionIntervalCalculatorTest
 *
 * @author xingxun
 */
public class TaskExecutionIntervalCalculatorTest extends BaseTaskUnitTest {

    @Test
    public void calculateNextRetryTime() {

        int interval = 3;
        String intervalExpression = String.valueOf(interval);

        String dateFormat = "yyyy-MM-dd HH:mm";

        Date actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(null,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat), DateUtil.dateFormat(new Date(), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime("",  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat), DateUtil.dateFormat(new Date(), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  2);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  3);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, 1 * interval), dateFormat));

        int multipliper = 2;
        intervalExpression = String.format("%s,%s", interval, multipliper);
        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  1);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 1 * multipliper), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  2);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 2 * multipliper), dateFormat));

        actual = TaskExecutionIntervalCalculator.calculateNextRetryTime(intervalExpression,  3);
        Assert.assertEquals(DateUtil.dateFormat(actual, dateFormat),
                DateUtil.dateFormat(DateUtil.modifyTime(new Date(), TimeUnit.SECONDS, interval * 3 * multipliper), dateFormat));
    }
}