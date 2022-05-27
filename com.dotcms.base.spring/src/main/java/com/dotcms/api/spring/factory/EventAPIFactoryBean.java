package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.calendar.business.EventAPI;
import org.springframework.beans.factory.FactoryBean;

public class EventAPIFactoryBean implements FactoryBean<EventAPI> {

    @Override
    public EventAPI getObject() throws Exception {
        return APILocator.getEventAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return EventAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
