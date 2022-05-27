package com.dotcms.api.spring.factory;

import com.dotcms.content.elasticsearch.business.ContentletIndexAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ContentletIndexAPIFactoryBean implements FactoryBean<ContentletIndexAPI> {

    @Override
    public ContentletIndexAPI getObject() throws Exception {
        return APILocator.getContentletIndexAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ContentletIndexAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
