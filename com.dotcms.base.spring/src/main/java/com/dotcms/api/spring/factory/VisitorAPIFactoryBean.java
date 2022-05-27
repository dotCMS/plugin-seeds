package com.dotcms.api.spring.factory;

import com.dotcms.visitor.business.VisitorAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class VisitorAPIFactoryBean implements FactoryBean<VisitorAPI> {

    @Override
    public VisitorAPI getObject() throws Exception {
        return APILocator.getVisitorAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return VisitorAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
