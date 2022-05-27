package com.dotcms.api.spring.factory;

import com.dotcms.publisher.business.PublishAuditAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class PublishAuditAPIFactoryBean implements FactoryBean<PublishAuditAPI> {

    @Override
    public PublishAuditAPI getObject() throws Exception {
        return APILocator.getPublishAuditAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PublishAuditAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
