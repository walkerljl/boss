package org.walkerljl.boss.support.mvc.i18n.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.walkerljl.boss.support.mvc.i18n.LocaleUtils;
import org.walkerljl.boss.support.mvc.i18n.MessageResourceService;

/**
 * @author lijunlin
 */
public class MessageResourceServiceImpl implements MessageResourceService {

    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String get(String key, Object... args) {
        try {
            if (messageSource != null) {
                String message = messageSource.getMessage(key, args, key, LocaleUtils.getLocale());
                return message;
            }
            return key;
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    @Override
    public String getMessage(String key, Object... args) {
        return get(key, args);
    }

}
