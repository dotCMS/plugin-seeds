package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.templates.business.TemplateAPI;
import org.springframework.beans.factory.FactoryBean;

public class TemplateAPIFactoryBean implements FactoryBean<TemplateAPI> {

    @Override
    public TemplateAPI getObject() throws Exception {
        return APILocator.getTemplateAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return TemplateAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
