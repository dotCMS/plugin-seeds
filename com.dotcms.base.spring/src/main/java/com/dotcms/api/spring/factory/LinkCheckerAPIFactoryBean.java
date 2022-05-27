package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.linkchecker.business.LinkCheckerAPI;
import org.springframework.beans.factory.FactoryBean;

public class LinkCheckerAPIFactoryBean implements FactoryBean<LinkCheckerAPI> {

    @Override
    public LinkCheckerAPI getObject() throws Exception {
        return APILocator.getLinkCheckerAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkCheckerAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
