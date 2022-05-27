package com.dotcms.api.spring.factory;

import com.dotcms.cms.login.LoginServiceAPI;
import com.dotcms.enterprise.cache.provider.CacheProviderAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class LoginServiceAPIFactoryBean implements FactoryBean<LoginServiceAPI> {

    @Override
    public LoginServiceAPI getObject() throws Exception {
        return APILocator.getLoginServiceAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LoginServiceAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
