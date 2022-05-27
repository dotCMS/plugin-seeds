package com.dotcms.api.spring.factory;

import com.dotcms.uuid.shorty.ShortyIdAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ShortyIdAPIFactoryBean implements FactoryBean<ShortyIdAPI> {

    @Override
    public ShortyIdAPI getObject() throws Exception {
        return APILocator.getShortyAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ShortyIdAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
