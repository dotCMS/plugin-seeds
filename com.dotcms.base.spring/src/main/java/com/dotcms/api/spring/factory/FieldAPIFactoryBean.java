package com.dotcms.api.spring.factory;

import com.dotcms.contenttype.business.FieldAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class FieldAPIFactoryBean implements FactoryBean<FieldAPI> {

    @Override
    public FieldAPI getObject() throws Exception {
        return APILocator.getContentTypeFieldAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FieldAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
