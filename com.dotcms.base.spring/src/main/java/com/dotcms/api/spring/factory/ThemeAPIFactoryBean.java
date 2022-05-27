package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.ThemeAPI;
import org.springframework.beans.factory.FactoryBean;

public class ThemeAPIFactoryBean implements FactoryBean<ThemeAPI> {

    @Override
    public ThemeAPI getObject() throws Exception {
        return APILocator.getThemeAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ThemeAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
