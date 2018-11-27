package org.walkerljl.boss.service.retry.demo.service.impl;

import org.walkerljl.boss.service.retry.demo.service.User;
import org.walkerljl.boss.service.retry.demo.service.UserService;
import org.walkerljl.toolkit.template.log.Logger;
import org.walkerljl.toolkit.template.log.LoggerFactory;

/**
 *
 * @author xingxun
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void save(User user) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Add one user:%s.", user.toString()));
        }
    }
}