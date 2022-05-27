package com.dotcms.api.spring.factory;

import com.dotcms.publisher.assets.business.PushedAssetsAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class PushedAssetsAPIFactoryBean implements FactoryBean<PushedAssetsAPI> {

    @Override
    public PushedAssetsAPI getObject() throws Exception {
        return APILocator.getPushedAssetsAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PushedAssetsAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
