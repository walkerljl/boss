package org.walkerljl.boss.support.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.walkerljl.toolkit.lang.MapUtils;
import org.walkerljl.toolkit.logging.Logger;
import org.walkerljl.toolkit.logging.LoggerFactory;
import org.walkerljl.toolkit.standard.Machine;
import org.walkerljl.toolkit.standard.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.exception.machine.CannotStartMachineException;
import org.walkerljl.toolkit.standard.exception.machine.CannotStopMachineException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 启动类
 *
 * @author xingxun
 */
@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class Bootstrap extends AbstractMachine implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    private boolean isRunning = false;

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public void start() throws CannotStartMachineException {
        synchronized (this) {
            if (isRunning) {
                return;
            }
            isRunning = true;
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Has started.");
            }
        }
    }

    @Override
    public void stop() throws CannotStopMachineException {
        synchronized (this) {
            if (!isRunning) {
                return;
            }
            isRunning = false;
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Has stopped.");
            }
        }
    }

    /**
     * 状态检测
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/status")
    public ModelAndView status(HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = MapUtils.newHashMap();
        resultMap.put("message", (isRunning ? "Running..." : "Stopped..."));
        resultMap.put("code", (isRunning ? "alive" : "dead"));
        //JSONUt.write(response.getWriter(), resultMap, null);
        //if (LOGGER.isInfoEnabled()) {
        //    LOGGER.info(String.format("Check status,result:%s.", JSONUtils.toJSONString(resultMap)));
        //}
        return null;
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