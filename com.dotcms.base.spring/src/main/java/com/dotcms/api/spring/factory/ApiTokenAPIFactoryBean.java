package com.dotcms.api.spring.factory;

import com.dotcms.auth.providers.jwt.factories.ApiTokenAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ApiTokenAPIFactoryBean implements FactoryBean<ApiTokenAPI> {

    @Override
    public ApiTokenAPI getObject() throws Exception {
        return APILocator.getApiTokenAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ApiTokenAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
