package com.dotcms.api.spring.factory;

import com.dotcms.notifications.business.NotificationAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class NotificationAPIFactoryBean implements FactoryBean<NotificationAPI> {

    @Override
    public NotificationAPI getObject() throws Exception {
        return APILocator.getNotificationAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return NotificationAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
