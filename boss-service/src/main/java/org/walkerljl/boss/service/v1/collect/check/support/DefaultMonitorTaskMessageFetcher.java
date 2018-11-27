package org.walkerljl.boss.service.v1.collect.check.support;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.walkerljl.boss.model.enums.monitor.MonitorObjectType;
import org.walkerljl.boss.service.v1.collect.Checker;
import org.walkerljl.boss.service.v1.collect.check.MonitorTaskMessageFetcher;
import org.walkerljl.boss.service.v1.collect.check.listener.MonitorTaskMessageListener;
import org.walkerljl.boss.service.v1.collect.task.MonitorTask;
import org.walkerljl.boss.service.v1.collect.task.MonitorTaskQueue;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.lang.thread.NamedThreadFactory;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * DefaultMonitorTaskMessageFetcher
 *
 * @author lijunlin
 */
@Component("monitorTaskMessageFetcher")
public class DefaultMonitorTaskMessageFetcher extends AbstractMachine implements MonitorTaskMessageFetcher {

    private static final Logger  LOGGER       = LoggerFactory.getLogger(DefaultMonitorTaskMessageFetcher.class);
    private static final String  OBJECT_NAME  = "MonitorTaskMessageFetcher";
    private              Integer threadAmount = 1;//Constants.getConfigurator().getAsInteger("monitor.task.message.fetch.thread.amount", 1);
    @Resource
    private MonitorTaskMessageListener monitorTaskMessageListener;
    @Resource
    private MonitorTaskQueue           monitorTaskQueue;

    @Resource
    private Checker httpChecker;
    @Resource
    private Checker dnsSwitchableHttpChecker;

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public void start() throws CannotStartMachineException {
        CheckerRepository.bind(MonitorObjectType.HTTP, httpChecker);
        CheckerRepository.bind(MonitorObjectType.DNS_SWITCHABLE_HTTP, dnsSwitchableHttpChecker);

        Map<MonitorObjectType, Checker> checkerMap = CheckerRepository.list();
        if (MapUtils.isEmpty(checkerMap)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("%s has started,no monitor task message fetch fetcher has been started.", OBJECT_NAME));
            }
            return;
        }
        for (Checker checker : checkerMap.values()) {
            NamedThreadFactory namedThreadFactory = new NamedThreadFactory(checker.getClass().getSimpleName());
            for (int i = 0; i < threadAmount; i++) {
                namedThreadFactory.newThread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                long sleepTime
                                        = -1L;//Constants.getConfigurator().getAsLong("monitor.task.message.fetch.thread.sleeptime", -1L);
                                if (sleepTime > 0) {
                                    Thread.sleep(sleepTime);
                                    if (LOGGER.isDebugEnabled()) {
                                        LOGGER.debug(String.format("%s sleep %s mills.", Thread.currentThread().getName(), sleepTime));
                                    }
                                }
                                MonitorTask monitorTask = monitorTaskQueue.pull();
                                monitorTaskMessageListener.onMessage(monitorTask);
                            } catch (Throwable e) {
                                LOGGER.error(String.format("%s occurs one error.", OBJECT_NAME), e);
                            }
                        }
                    }
                }).start();
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s has started.", OBJECT_NAME));
        }
    }

    @Override
    public void stop() throws CannotStopMachineException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s has stopped.", OBJECT_NAME));
        }
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
