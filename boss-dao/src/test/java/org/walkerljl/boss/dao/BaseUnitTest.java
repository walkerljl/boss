package org.walkerljl.boss.dao;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * BaseUnitTest
 *
 * @author lijunlin
 */
public abstract class BaseUnitTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(BaseUnitTest.class);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        LOGGER.debug("Do some initialization.");
    }

    @After
    public void after() {
        LOGGER.debug("Do some release.");

    }
}
