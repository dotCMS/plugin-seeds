package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.categories.business.CategoryAPI;
import com.dotmarketing.portlets.contentlet.business.ContentletAPI;
import org.springframework.beans.factory.FactoryBean;

public class ContentletAPIFactoryBean implements FactoryBean<ContentletAPI> {

    @Override
    public ContentletAPI getObject() throws Exception {
        return APILocator.getContentletAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ContentletAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
