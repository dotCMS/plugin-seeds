package com.dotcms.api.spring.factory;

import com.dotcms.enterprise.ESSeachAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ESSeachAPIFactoryBean implements FactoryBean<ESSeachAPI> {

    @Override
    public ESSeachAPI getObject() throws Exception {
        return APILocator.getEsSearchAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ESSeachAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
