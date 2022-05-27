package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.contentlet.business.HostAPI;
import org.springframework.beans.factory.FactoryBean;

public class HostAPIFactoryBean implements FactoryBean<HostAPI> {

    @Override
    public HostAPI getObject() throws Exception {
        return APILocator.getHostAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return HostAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
