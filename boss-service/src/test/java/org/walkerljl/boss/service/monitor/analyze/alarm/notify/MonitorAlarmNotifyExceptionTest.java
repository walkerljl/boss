package org.walkerljl.boss.service.monitor.analyze.alarm.notify;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.base.BaseUnitTest;
import org.walkerljl.boss.service.monitor.exception.MonitorException;
import org.walkerljl.toolkit.standard.enums.IEnum;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * MonitorAlarmNotifyExceptionTest
 *
 * @author xingxun
 */
public class MonitorAlarmNotifyExceptionTest extends BaseUnitTest {

    @Test
    public void test() {

        Assert.assertTrue(new MonitorAlarmNotifyException() instanceof MonitorException);

        ErrorCode expectedErrorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "code";
            }

            @Override
            public String getDescription() {
                return "description";
            }

            @Override
            public IEnum getEnumObject(String code) {
                return null;
            }
        };

        String expectedErrorMsg = "errorMsg";
        MonitorAlarmNotifyException actual = new MonitorAlarmNotifyException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new MonitorAlarmNotifyException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new MonitorAlarmNotifyException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAlarmNotifyException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new MonitorAlarmNotifyException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new MonitorAlarmNotifyException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAlarmNotifyException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAlarmNotifyException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);
    }

}