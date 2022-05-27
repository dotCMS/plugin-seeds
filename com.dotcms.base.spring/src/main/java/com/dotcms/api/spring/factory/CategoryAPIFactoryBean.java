package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.categories.business.CategoryAPI;
import org.springframework.beans.factory.FactoryBean;

public class CategoryAPIFactoryBean implements FactoryBean<CategoryAPI> {

    @Override
    public CategoryAPI getObject() throws Exception {
        return APILocator.getCategoryAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return CategoryAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
