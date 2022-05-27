package com.dotcms.api.spring.factory;

import com.dotcms.util.SecurityLoggerServiceAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class SecurityLoggerServiceAPIFactoryBean implements FactoryBean<SecurityLoggerServiceAPI> {

    @Override
    public SecurityLoggerServiceAPI getObject() throws Exception {
        return APILocator.getSecurityLogger();
    }

    @Override
    public Class<?> getObjectType() {
        return SecurityLoggerServiceAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
