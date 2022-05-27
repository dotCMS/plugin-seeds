package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.portal.PortletAPI;
import org.springframework.beans.factory.FactoryBean;

public class PortletAPIFactoryBean implements FactoryBean<PortletAPI> {

    @Override
    public PortletAPI getObject() throws Exception {
        return APILocator.getPortletAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PortletAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
