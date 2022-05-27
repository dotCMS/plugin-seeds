package com.dotcms.api.spring.factory;

import com.dotcms.languagevariable.business.LanguageVariableAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class LanguageVariableAPIFactoryBean implements FactoryBean<LanguageVariableAPI> {

    @Override
    public LanguageVariableAPI getObject() throws Exception {
        return APILocator.getLanguageVariableAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LanguageVariableAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
