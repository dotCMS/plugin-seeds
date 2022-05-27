package com.dotcms.api.spring.factory;

import com.dotcms.system.event.local.business.LocalSystemEventsAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class LocalSystemEventsAPIFactoryBean implements FactoryBean<LocalSystemEventsAPI> {

    @Override
    public LocalSystemEventsAPI getObject() throws Exception {
        return APILocator.getLocalSystemEventsAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LocalSystemEventsAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
