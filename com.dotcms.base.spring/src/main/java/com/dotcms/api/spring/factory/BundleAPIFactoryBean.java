package com.dotcms.api.spring.factory;

import com.dotcms.publisher.bundle.business.BundleAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class BundleAPIFactoryBean implements FactoryBean<BundleAPI> {

    @Override
    public BundleAPI getObject() throws Exception {
        return APILocator.getBundleAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return BundleAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
