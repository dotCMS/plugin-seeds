package com.dotcms.api.spring.factory;

import com.dotcms.content.elasticsearch.business.IndiciesAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class IndiciesAPIFactoryBean implements FactoryBean<IndiciesAPI> {

    @Override
    public IndiciesAPI getObject() throws Exception {
        return APILocator.getIndiciesAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return IndiciesAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
