package org.walkerljl.boss.service.schedule;

import org.walkerljl.scheduler.Job;
import org.walkerljl.scheduler.JobExecutionContext;
import org.walkerljl.scheduler.exception.JobExecutionException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;

public class PrintHelloWorldJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintHelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Hello world.");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
