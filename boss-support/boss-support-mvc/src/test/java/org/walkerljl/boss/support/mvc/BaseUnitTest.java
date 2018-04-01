package org.walkerljl.boss.support.mvc;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
