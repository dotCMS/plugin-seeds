package com.dotcms.api.spring.factory;

import com.dotcms.publisher.endpoint.business.PublishingEndPointAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class PublishingEndPointAPIFactoryBean implements FactoryBean<PublishingEndPointAPI> {

    @Override
    public PublishingEndPointAPI getObject() throws Exception {
        return APILocator.getPublisherEndPointAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PublishingEndPointAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
