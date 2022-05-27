package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.factories.MultiTreeAPI;
import org.springframework.beans.factory.FactoryBean;

public class MultiTreeAPIFactoryBean implements FactoryBean<MultiTreeAPI> {

    @Override
    public MultiTreeAPI getObject() throws Exception {
        return APILocator.getMultiTreeAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return MultiTreeAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
