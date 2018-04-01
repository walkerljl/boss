package org.walkerljl.boss.service.monitor.collect.task.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.common.Constants;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorObjectDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorObjectDO;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTask;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTaskDispatcher;
import org.walkerljl.boss.service.monitor.collect.task.MonitorTaskQueue;
import org.walkerljl.scheduler.JobExecutionContext;
import org.walkerljl.scheduler.exception.JobExecutionException;
import org.walkerljl.toolkit.lang.CollectionUtils;
import org.walkerljl.toolkit.lang.thread.NamedThreadFactory;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.template.compute.AsyncParallelComputeTempalte;

/**
 * MonitorTaskDispatcherImpl
 *
 * @author lijunlin
 */
@Component("monitorTaskDispatcher")
public class MonitorTaskDispatcherImpl implements MonitorTaskDispatcher {

    private static final Logger             LOGGER                   = LoggerFactory.getLogger(MonitorTaskDispatcherImpl.class);
    private static final String             KEY_PREFIX               = "monitor.task.dispatcher";
    private              ThreadPoolExecutor taskDispatcherThreadPool = new ThreadPoolExecutor(
            Constants.getConfigurator().getAsInteger(KEY_PREFIX + ".threadpool.coresize", 1),
            Constants.getConfigurator().getAsInteger(KEY_PREFIX + ".threadpool.maximumPoolSize", 100),
            Constants.getConfigurator().getAsLong(KEY_PREFIX + ".threadpool.keepAliveTime", 60L),
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(Constants.getConfigurator()
                    .getAsInteger(KEY_PREFIX + ".workqueue.capacity", 1000)),
            new NamedThreadFactory(KEY_PREFIX)
    );
    private int dispatchPageSize = Constants.getConfigurator().getAsInteger(KEY_PREFIX + ".pagesize", 100);

    @Resource
    private MonitorTaskQueue monitorTaskQueue;
    @Resource
    private MonitorObjectDAO monitorObjectDAO;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            int count = monitorObjectDAO.selectListCount(new MonitorObjectDO());
            if (count <= 0) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Query zero monitor object record.");
                }
                return;
            }
            List<Integer> params = new ArrayList<Integer>();
            for (int currentPage = 1; currentPage <= ((count / dispatchPageSize) + 1); currentPage++) {
                params.add(currentPage);
            }
            new AsyncParallelComputeTempalte<Integer, Object>() {
                @Override
                public Object compute0(Integer currentPage) {
                    dispatch(currentPage, dispatchPageSize);
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("Dispatch finished.");
                    }
                    return null;
                }
            }.compute(taskDispatcherThreadPool, params);
        } catch (Throwable e) {
            LOGGER.error("Monitor task dispatch error.", e);
        }
    }

    /**
     * 派发任务
     *
     * @param currentPage
     * @param pageSize
     */
    private void dispatch(int currentPage, int pageSize) {
        List<MonitorObjectDO> monitorObjects = monitorObjectDAO.selectList(new MonitorObjectDO(), currentPage, pageSize);
        if (CollectionUtils.isEmpty(monitorObjects)) {
            return;
        }
        for (MonitorObjectDO monitorObject : monitorObjects) {
            MonitorTask monitorTask = new MonitorTask();
            monitorTask.setAppId(monitorObject.getAppId());
            monitorTask.setMonitorObjectId(monitorObject.getId());
            monitorTask.setType(MonitorObjectType.getType(monitorObject.getType()));
            monitorTask.setTarget(monitorObject.getTarget());
            monitorTask.setCreated(new Date());
            monitorTaskQueue.add(monitorTask);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("Dispatch one monitor task->%s", monitorTask.toString()));
            }
        }
    }
}