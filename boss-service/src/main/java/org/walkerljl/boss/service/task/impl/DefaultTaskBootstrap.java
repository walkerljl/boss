package org.walkerljl.boss.service.task.impl;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.walkerljl.boss.common.util.CollectionUtil;
import org.walkerljl.boss.service.task.TaskBootstrap;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchExecutor;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchLoader;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchSplitor;
import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.MachineRepository;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.machine.impl.MachineRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.standard.resource.impl.ResourceRepositoryFactory;
import org.walkerljl.toolkit.template.handle.service.ServiceHandleTemplate;
import org.walkerljl.toolkit.template.handle.service.ServiceHandler;
import org.walkerljl.toolkit.template.log.model.InvocationInfo;

/**
 * 默认的任务引导程序
 *
 * @author xingxun
 */
public class DefaultTaskBootstrap extends AbstractMachine implements TaskBootstrap {

    private ResourceRepository resourceRepository = ResourceRepositoryFactory.getDefaultRepository();
    private MachineRepository  machineRepository  = MachineRepositoryFactory.getDefaultRepository();

    private TaskDispatchSplitor  taskDispatchSplitor;
    private TaskDispatchLoader   taskDispatchLoader;
    private TaskDispatchExecutor taskDispatchExecutor;

    @Override
    public void processStart() throws CannotStartMachineException {
        for (Machine machine : machineRepository.lookupAll()) {
            if (getGroup().equalsIgnoreCase(machine.getGroup())
                    && getId().equalsIgnoreCase(machine.getId())) {
                continue;
            }
            machine.start();
        }

        new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable command) {
                return new Thread(command);
            }
        }).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        InvocationInfo<Void, Void> invocationInfo =
                                new InvocationInfo<>(getClass(), "run", null);
                        ServiceHandleTemplate.getInstance().handle(invocationInfo, new ServiceHandler<Void, Void>() {
                            @Override
                            public boolean checkParams(Void aVoid) {
                                return true;
                            }

                            @Override
                            public Void handle(Void aVoid) {
                                processStart0();
                                return null;
                            }
                        });
                    }
                }
                , 5, 5, TimeUnit.SECONDS);
    }

    /**
     * 处理启动业务逻辑
     */
    private void processStart0() {
        List<SplitedTaskItem> splitTaskItems = taskDispatchSplitor.split();
        if (CollectionUtil.isEmpty(splitTaskItems)) {
            return;
        }
        for (SplitedTaskItem splitTaskItem : splitTaskItems) {
            List<String> taskIds = taskDispatchLoader.load(splitTaskItem);
            if (org.walkerljl.boss.common.util.CollectionUtil.isEmpty(taskIds)) {
                continue;
            }
            for (String taskId : taskIds) {
                taskDispatchExecutor.execute(taskId);
            }
        }
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        for (Machine machine : machineRepository.lookupAll()) {
            if (getGroup().equalsIgnoreCase(machine.getGroup())
                    && getId().equalsIgnoreCase(machine.getId())) {
                continue;
            }
            machine.stop();
        }
    }

    @Override
    public void processInit() throws CannotInitResourceException {
        for (Resource resource : resourceRepository.lookupAll()) {
            if (getGroup().equalsIgnoreCase(resource.getGroup())
                    && getId().equalsIgnoreCase(resource.getId())) {
                continue;
            }
            resource.init();
        }
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        for (Resource resource : resourceRepository.lookupAll()) {
            if (getGroup().equalsIgnoreCase(resource.getGroup())
                    && getId().equalsIgnoreCase(resource.getId())) {
                continue;
            }
            resource.destroy();
        }
    }

    /**
     * Setter method for property <tt>taskDispatchSplitor</tt>.
     *
     * @param taskDispatchSplitor  value to be assigned to property taskDispatchSplitor
     */
    public void setTaskDispatchSplitor(TaskDispatchSplitor taskDispatchSplitor) {
        this.taskDispatchSplitor = taskDispatchSplitor;
    }

    /**
     * Setter method for property <tt>taskDispatchLoader</tt>.
     *
     * @param taskDispatchLoader  value to be assigned to property taskDispatchLoader
     */
    public void setTaskDispatchLoader(TaskDispatchLoader taskDispatchLoader) {
        this.taskDispatchLoader = taskDispatchLoader;
    }

    /**
     * Setter method for property <tt>taskDispatchExecutor</tt>.
     *
     * @param taskDispatchExecutor  value to be assigned to property taskDispatchExecutor
     */
    public void setTaskDispatchExecutor(TaskDispatchExecutor taskDispatchExecutor) {
        this.taskDispatchExecutor = taskDispatchExecutor;
    }
}