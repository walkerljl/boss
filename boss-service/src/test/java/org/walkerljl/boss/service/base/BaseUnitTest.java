package org.walkerljl.boss.service.base;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.walkerljl.boss.common.log.LoggerResource;

/**
 * BaseUnitTest
 *
 * @author xingxun
 */
public class BaseUnitTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        new LoggerResource().init();
    }

    @After
    public void destroy() {

    }
}