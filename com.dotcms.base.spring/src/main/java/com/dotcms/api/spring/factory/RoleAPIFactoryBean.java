package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.RoleAPI;
import org.springframework.beans.factory.FactoryBean;

public class RoleAPIFactoryBean implements FactoryBean<RoleAPI> {

    @Override
    public RoleAPI getObject() throws Exception {
        return APILocator.getRoleAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return RoleAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
