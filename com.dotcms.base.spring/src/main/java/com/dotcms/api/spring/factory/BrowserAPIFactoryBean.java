package com.dotcms.api.spring.factory;

import com.dotcms.browser.BrowserAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class BrowserAPIFactoryBean implements FactoryBean<BrowserAPI> {

    @Override
    public BrowserAPI getObject() throws Exception {
        return APILocator.getBrowserAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return BrowserAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
