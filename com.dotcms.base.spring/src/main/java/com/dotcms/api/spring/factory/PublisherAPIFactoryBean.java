package com.dotcms.api.spring.factory;

import com.dotcms.publishing.PublisherAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class PublisherAPIFactoryBean implements FactoryBean<PublisherAPI> {

    @Override
    public PublisherAPI getObject() throws Exception {
        return APILocator.getPublisherAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PublisherAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
