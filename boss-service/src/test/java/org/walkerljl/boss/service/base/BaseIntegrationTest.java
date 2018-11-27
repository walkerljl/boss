package org.walkerljl.boss.service.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseIntegration
 *
 * @author lijunlin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public abstract class BaseIntegrationTest {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Before
    public void init() {

    }

    @After
    public void destroy() {

    }
}
