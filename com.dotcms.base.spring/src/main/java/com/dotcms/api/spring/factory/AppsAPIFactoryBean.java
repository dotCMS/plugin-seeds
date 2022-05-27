package com.dotcms.api.spring.factory;

import com.dotcms.security.apps.AppsAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class AppsAPIFactoryBean implements FactoryBean<AppsAPI> {

    @Override
    public AppsAPI getObject() throws Exception {
        return APILocator.getAppsAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return AppsAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
