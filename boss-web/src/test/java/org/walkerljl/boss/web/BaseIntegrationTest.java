package org.walkerljl.boss.web;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;

/**
 * BaseTest
 *
 * @author lijunlin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public abstract class BaseIntegrationTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Before
    public void before() {

    }

    @After
    public void after() {

    }
}
