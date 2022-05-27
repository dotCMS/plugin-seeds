package com.dotcms.api.spring.factory;

import com.dotcms.contenttype.business.ContentTypeFieldLayoutAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ContentTypeFieldLayoutAPIFactoryBean implements FactoryBean<ContentTypeFieldLayoutAPI> {

    @Override
    public ContentTypeFieldLayoutAPI getObject() throws Exception {
        return APILocator.getContentTypeFieldLayoutAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ContentTypeFieldLayoutAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
