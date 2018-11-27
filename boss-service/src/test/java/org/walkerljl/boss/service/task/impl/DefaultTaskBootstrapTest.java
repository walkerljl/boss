package org.walkerljl.boss.service.task.impl;

import java.util.List;

import org.junit.Test;
import org.walkerljl.boss.service.task.BaseTaskUnitTest;
import org.walkerljl.boss.service.task.shell.dispatch.SplitedTaskItem;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchExecutor;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchLoader;
import org.walkerljl.boss.service.task.shell.dispatch.TaskDispatchSplitor;
import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * DefaultTaskBootstrapTest
 *
 * @author xingxun
 */
public class DefaultTaskBootstrapTest extends BaseTaskUnitTest {

    @Test
    public void test() {

        DefaultTaskBootstrap taskBootstrap = new DefaultTaskBootstrap();
        taskBootstrap.setTaskDispatchSplitor(new DefaultTaskDispatchSplitor2());
        taskBootstrap.setTaskDispatchLoader(new DefaultTaskDispatchLoader2());
        taskBootstrap.setTaskDispatchExecutor(new DefaultTaskDispatchExecutor2());

        DefaultResource resource = new DefaultResource();
        resource.init();

        taskBootstrap.init();
        taskBootstrap.start();
        taskBootstrap.destroy();
        taskBootstrap.stop();
    }

    @Test
    public void init() {

    }

    @Test
    public void destroy() {

    }

    @Test
    public void start() {

    }

    @Test
    public void stop() {

    }
}

class DefaultResource extends AbstractResource implements Resource {

    private boolean inited = false;

    @Override
    public void processInit() throws CannotInitResourceException {
        inited = true;
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        inited = false;
    }

    /**
     * Getter method for property <tt>inited</tt>.
     *
     * @return property value of inited
     */
    public boolean isInited() {
        return inited;
    }
}

class DefaultMachine extends AbstractMachine implements Machine {

    private boolean started = false;

    @Override
    public void processStart() throws CannotStartMachineException {
        started = true;
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        started = false;
    }

    /**
     * Getter method for property <tt>started</tt>.
     *
     * @return property value of started
     */
    public boolean isStarted() {
        return started;
    }
}

class DefaultTaskDispatchSplitor2 implements TaskDispatchSplitor {

    @Override
    public List<SplitedTaskItem> split() {
        return null;
    }
}


class DefaultTaskDispatchLoader2 implements TaskDispatchLoader {

    @Override
    public List<String> load(SplitedTaskItem splitedTaskItem) {
        return null;
    }
}


class DefaultTaskDispatchExecutor2 implements TaskDispatchExecutor {

    @Override
    public void execute(String taskId) {

    }
}