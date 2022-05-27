package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.image.focalpoint.FocalPointAPI;
import org.springframework.beans.factory.FactoryBean;

public class FocalPointAPIFactoryBean implements FactoryBean<FocalPointAPI> {

    @Override
    public FocalPointAPI getObject() throws Exception {
        return APILocator.getFocalPointAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FocalPointAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
