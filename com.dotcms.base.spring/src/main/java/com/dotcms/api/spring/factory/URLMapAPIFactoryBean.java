package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.cms.urlmap.URLMapAPI;
import org.springframework.beans.factory.FactoryBean;

public class URLMapAPIFactoryBean implements FactoryBean<URLMapAPI> {

    @Override
    public URLMapAPI getObject() throws Exception {
        return APILocator.getURLMapAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return URLMapAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
