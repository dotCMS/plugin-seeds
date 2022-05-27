package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.VersionableAPI;
import org.springframework.beans.factory.FactoryBean;

public class VersionableAPIFactoryBean implements FactoryBean<VersionableAPI> {

    @Override
    public VersionableAPI getObject() throws Exception {
        return APILocator.getVersionableAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return VersionableAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
