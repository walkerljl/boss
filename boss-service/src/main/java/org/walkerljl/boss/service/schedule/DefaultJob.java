package org.walkerljl.boss.service.schedule;

import org.walkerljl.scheduler.Job;
import org.walkerljl.scheduler.JobExecutionContext;
import org.walkerljl.scheduler.exception.JobExecutionException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;

/**
 * DefaultJob
 *
 * @author lijunlin
 */
public class DefaultJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Hello.");
        }
    }
}
