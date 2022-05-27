package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.PermissionAPI;
import org.springframework.beans.factory.FactoryBean;

public class PermissionAPIFactoryBean implements FactoryBean<PermissionAPI> {

    @Override
    public PermissionAPI getObject() throws Exception {
        return APILocator.getPermissionAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PermissionAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
