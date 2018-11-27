package org.walkerljl.boss.service.schedule;

import org.walkerljl.scheduler.JobExecutionContext;
import org.walkerljl.scheduler.filter.JobFilter;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;

/**
 * DefaultJobFilter
 *
 * @author lijunlin
 */
public class DefaultJobFilter implements JobFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJob.class);

    @Override
    public boolean before(JobExecutionContext context) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Do something before.");
        }
        return true;
    }

    @Override
    public boolean after(JobExecutionContext context) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Do something after.");
        }
        return true;
    }
}
