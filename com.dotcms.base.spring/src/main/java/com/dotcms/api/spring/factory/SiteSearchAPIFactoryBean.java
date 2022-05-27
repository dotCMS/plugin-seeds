package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.sitesearch.business.SiteSearchAPI;
import org.springframework.beans.factory.FactoryBean;

public class SiteSearchAPIFactoryBean implements FactoryBean<SiteSearchAPI> {

    @Override
    public SiteSearchAPI getObject() throws Exception {
        return APILocator.getSiteSearchAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return SiteSearchAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
