package com.dotcms.api.spring.factory;

import com.dotcms.content.elasticsearch.business.ESIndexAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ESIndexAPIFactoryBean implements FactoryBean<ESIndexAPI> {

    @Override
    public ESIndexAPI getObject() throws Exception {
        return APILocator.getESIndexAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ESIndexAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
