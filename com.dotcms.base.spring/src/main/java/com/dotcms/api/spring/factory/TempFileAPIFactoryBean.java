package com.dotcms.api.spring.factory;

import com.dotcms.rest.api.v1.temp.TempFileAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class TempFileAPIFactoryBean implements FactoryBean<TempFileAPI> {

    @Override
    public TempFileAPI getObject() throws Exception {
        return APILocator.getTempFileAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return TempFileAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
