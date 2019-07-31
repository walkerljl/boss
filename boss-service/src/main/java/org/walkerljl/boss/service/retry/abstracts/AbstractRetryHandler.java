package org.walkerljl.boss.service.retry.abstracts;

import org.walkerljl.boss.service.retry.RetryHandler;
import org.walkerljl.boss.service.retry.impl.RetryContext;
import org.walkerljl.boss.service.retry.impl.RetryHandlerRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * AbstractRetryHandler
 *
 * @author xingxun
 */
public abstract class AbstractRetryHandler extends AbstractResource implements RetryHandler {

    @Override
    public void processInit() throws CannotInitResourceException {
        RetryHandlerRepositoryFactory.getDefaultRepository().register(getClass().getSimpleName(), this);
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        RetryHandlerRepositoryFactory.getDefaultRepository().unregister(getClass().getSimpleName());
    }

    @Override
    public void handle(RetryContext context) {
        handle0(context);
    }

    /**
     * handle0
     *
     * @param context
     */
    public abstract void handle0(RetryContext context);
}