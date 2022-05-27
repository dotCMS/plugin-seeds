package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.UserAPI;
import org.springframework.beans.factory.FactoryBean;

public class UserAPIFactoryBean implements FactoryBean<UserAPI> {

    @Override
    public UserAPI getObject() throws Exception {
        return APILocator.getUserAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return UserAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
