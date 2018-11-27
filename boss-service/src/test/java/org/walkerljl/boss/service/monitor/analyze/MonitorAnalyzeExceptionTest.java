package org.walkerljl.boss.service.monitor.analyze;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.boss.service.base.BaseUnitTest;
import org.walkerljl.boss.service.monitor.exception.MonitorException;
import org.walkerljl.toolkit.standard.enums.IEnum;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * MonitorAnalyzeExceptionTest
 *
 * @author xingxun
 */
public class MonitorAnalyzeExceptionTest extends BaseUnitTest {

    @Test
    public void test() {

        Assert.assertTrue(new MonitorAnalyzeException() instanceof MonitorException);

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
        MonitorAnalyzeException actual = new MonitorAnalyzeException();
        Assert.assertNull(actual.getCode());
        Assert.assertNull(actual.getMessage());

        actual = new MonitorAnalyzeException(expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new MonitorAnalyzeException(expectedThrowable);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAnalyzeException(expectedErrorCode);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());

        actual = new MonitorAnalyzeException(expectedErrorCode, expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);

        actual = new MonitorAnalyzeException(expectedErrorCode, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorCode.getDescription());
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAnalyzeException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertNull(actual.getCode());
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new MonitorAnalyzeException(expectedErrorCode, expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCode(), expectedErrorCode);
        Assert.assertEquals(actual.getCause(), expectedThrowable);
    }

}