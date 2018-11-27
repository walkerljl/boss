package org.walkerljl.boss.service.schedule;

import java.util.ArrayList;
import java.util.List;

import org.walkerljl.scheduler.JobDetail;
import org.walkerljl.scheduler.JobLoader;

/**
 * DefaultJobLoader
 *
 * @author lijunlin
 */
public class DefaultJobLoader implements JobLoader {

    @Override
    public List<JobDetail> load() {
        List<JobDetail> jobDetails = new ArrayList<>();
        JobDetail jobDetail = new JobDetail();
        jobDetails.add(jobDetail);
        return jobDetails;
    }
}

