package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.tag.business.TagAPI;
import org.springframework.beans.factory.FactoryBean;

public class TagAPIFactoryBean implements FactoryBean<TagAPI> {

    @Override
    public TagAPI getObject() throws Exception {
        return APILocator.getTagAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return TagAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
