package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.languagesmanager.business.LanguageAPI;
import org.springframework.beans.factory.FactoryBean;

public class LanguageAPIFactoryBean implements FactoryBean<LanguageAPI> {

    @Override
    public LanguageAPI getObject() throws Exception {
        return APILocator.getLanguageAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return LanguageAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
