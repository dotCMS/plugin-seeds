package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.LoginAsAPI;
import org.springframework.beans.factory.FactoryBean;

public class LoginAsAPIFactoryBean implements FactoryBean<LoginAsAPI> {

    @Override
    public LoginAsAPI getObject() throws Exception {
        return APILocator.getLoginAsAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LoginAsAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
