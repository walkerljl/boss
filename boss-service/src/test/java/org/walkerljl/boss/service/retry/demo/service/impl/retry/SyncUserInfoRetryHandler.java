package org.walkerljl.boss.service.retry.demo.service.impl.retry;

import java.util.List;

import org.walkerljl.boss.service.retry.RetryHandler;
import org.walkerljl.boss.service.retry.demo.service.UserService;
import org.walkerljl.boss.service.retry.demo.service.impl.UserServiceImpl;
import org.walkerljl.boss.service.retry.impl.RetryContext;
import org.walkerljl.boss.service.task.model.Task;
import org.walkerljl.boss.service.task.model.TaskParam;
import org.walkerljl.boss.support.common.util.JSONUtil;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;

/**
 *
 * @author xingxun
 */
public class SyncUserInfoRetryHandler implements RetryHandler {

    private UserService userService = new UserServiceImpl();

    @Override
    public void handle(RetryContext context) {
        //throw new AppServiceException("执行错误");

        Task task = (Task)context.getAttribute(RetryContext.KEY_TASK);
        List<TaskParam> retryParams = task.getParams();
        String userJSONString = retryParams.get(0).getValue();
        //User user = JSONUtil.parseObject(userJSONString, User.class);

        Logger logger = LoggerFactory.getLogger(getClass());
        if (logger.isInfoEnabled()) {
            logger.info(JSONUtil.toJSONString(userJSONString));
        }
    }

    @Override
    public String getInstanceId() {
        return null;
    }

    @Override
    public void init() throws CannotInitResourceException {

    }

    @Override
    public void destroy() throws CannotDestroyResourceException {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}