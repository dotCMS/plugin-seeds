package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.DeterministicIdentifierAPI;
import org.springframework.beans.factory.FactoryBean;

public class DeterministicIdentifierAPIFactoryBean implements FactoryBean<DeterministicIdentifierAPI> {

    @Override
    public DeterministicIdentifierAPI getObject() throws Exception {
        return APILocator.getDeterministicIdentifierAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return DeterministicIdentifierAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
