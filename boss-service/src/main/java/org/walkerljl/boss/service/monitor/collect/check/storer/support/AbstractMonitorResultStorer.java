package org.walkerljl.boss.service.monitor.collect.check.storer.support;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.walkerljl.boss.common.Constants;
import org.walkerljl.boss.dao.daointerface.monitor.MonitorResultDAO;
import org.walkerljl.boss.dao.dataobject.monitor.MonitorResultDO;
import org.walkerljl.boss.service.monitor.collect.check.storer.MonitorResultStorer;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.exception.machine.CannotStartMachineException;
import org.walkerljl.toolkit.standard.exception.machine.CannotStopMachineException;

/**
 * AbstractMonitorResultStorer
 *
 * @author lijunlin
 */
public class AbstractMonitorResultStorer extends AbstractMachine implements MonitorResultStorer {

    private final    Logger            LOGGER      = LoggerFactory.getLogger(getClass());
    private          int               batchSize   = Constants.getConfigurator().getAsInteger("batch.size.alarminfo.writer", 100);
    private          MonitorResultDO[] buffer      = new MonitorResultDO[batchSize];
    private volatile int               bufferIndex = 0;
    private volatile boolean           running     = false;
    private String                         serviceName;
    private BlockingQueue<MonitorResultDO> queue;
    private MonitorResultDAO               monitorResultDAO;


    @Override
    public void run() {
        while (queue.size() > 0 || running) {
            MonitorResultDO bufferElement = null;
            try {
                bufferElement = queue.take();
                synchronized (this) {
                    if (bufferIndex >= batchSize) {
                        store();
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(String.format("%s main thread store %s items.", getServiceName(), bufferIndex));
                        }
                    }
                    buffer[bufferIndex] = bufferElement;
                    bufferIndex++;
                }
            } catch (Throwable e) {
//                TraceUtils.traceExceptionQuietly(getServiceName() + " occurs error, skip and continue.",
//                        new Object[]{alarmInfo}, e);
            }
        }
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public void start() {
        synchronized (this) {
            if (!running) {
                initWorker();
                addShutdownHook();
                running = true;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("%s has started.", getServiceName()));
                }
            }
        }
    }

    @Override
    public void stop() {
        synchronized (this) {
            running = false;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("%s has stopped.", getServiceName()));
            }

        }
    }

    /**
     * Init worker thread.
     */
    private void initWorker() {
        long period = Constants.getConfigurator().getAsInteger("period.alarminfo.storer", 30);
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                store();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("{} worker thread store %s items.", getServiceName(), bufferIndex));
                }
            }
        }, 10, period, TimeUnit.SECONDS);
    }

    /**
     * Register shutdownhook, store the reservations before jvm shutdown.
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    this.stop();
                    store();
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(String.format("%s shutdownhook thread store %s items.", getServiceName(), bufferIndex));
                    }
                } catch (Throwable e) {
//                    TraceUtils.traceExceptionQuietly(getServiceName() + " do shutdownhook task occurs error, skip and continue.",
//                            new Object[]{buffer}, e);
                }
            }
        });
    }

    /**
     * Do store
     */
    private void store() {
        synchronized (this) {
            if (bufferIndex == 0) {
                return;
            }
            monitorResultDAO.insert(Arrays.asList(buffer));
            bufferIndex = 0;
            if (running) {
                buffer = new MonitorResultDO[batchSize];
            }
        }
    }

    /**
     * Get service name
     *
     * @return
     */
    private String getServiceName() {
        return serviceName;
    }

    @Override
    public void store(MonitorResultDO monitorResult) {
        queue.add(monitorResult);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}
