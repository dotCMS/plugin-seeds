package com.dotcms.api.spring.factory;

import com.dotcms.publisher.environment.business.EnvironmentAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class EnvironmentAPIFactoryBean implements FactoryBean<EnvironmentAPI> {

    @Override
    public EnvironmentAPI getObject() throws Exception {
        return APILocator.getEnvironmentAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return EnvironmentAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
