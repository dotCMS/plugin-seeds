package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.IdentifierAPI;
import org.springframework.beans.factory.FactoryBean;

public class IdentifierAPIFactoryBean implements FactoryBean<IdentifierAPI> {

    @Override
    public IdentifierAPI getObject() throws Exception {
        return APILocator.getIdentifierAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return IdentifierAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
