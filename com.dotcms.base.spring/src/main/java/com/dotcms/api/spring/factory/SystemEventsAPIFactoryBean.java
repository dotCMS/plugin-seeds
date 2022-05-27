package com.dotcms.api.spring.factory;

import com.dotcms.api.system.event.SystemEventsAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class SystemEventsAPIFactoryBean implements FactoryBean<SystemEventsAPI> {

    @Override
    public SystemEventsAPI getObject() throws Exception {
        return APILocator.getSystemEventsAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return SystemEventsAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
