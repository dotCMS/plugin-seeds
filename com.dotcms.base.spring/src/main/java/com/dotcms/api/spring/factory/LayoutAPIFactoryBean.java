package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.LayoutAPI;
import org.springframework.beans.factory.FactoryBean;

public class LayoutAPIFactoryBean implements FactoryBean<LayoutAPI> {

    @Override
    public LayoutAPI getObject() throws Exception {
        return APILocator.getLayoutAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LayoutAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
