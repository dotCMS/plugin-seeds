package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.sitesearch.business.SiteSearchAuditAPI;
import org.springframework.beans.factory.FactoryBean;

public class SiteSearchAuditAPIFactoryBean implements FactoryBean<SiteSearchAuditAPI> {

    @Override
    public SiteSearchAuditAPI getObject() throws Exception {
        return APILocator.getSiteSearchAuditAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return SiteSearchAuditAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
