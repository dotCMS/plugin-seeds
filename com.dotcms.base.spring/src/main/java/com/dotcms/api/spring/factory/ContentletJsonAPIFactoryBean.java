package com.dotcms.api.spring.factory;

import com.dotcms.content.business.json.ContentletJsonAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ContentletJsonAPIFactoryBean implements FactoryBean<ContentletJsonAPI> {

    @Override
    public ContentletJsonAPI getObject() throws Exception {
        return APILocator.getContentletJsonAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ContentletJsonAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
