package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.plugin.business.PluginAPI;
import org.springframework.beans.factory.FactoryBean;

public class PluginAPIFactoryBean implements FactoryBean<PluginAPI> {

    @Override
    public PluginAPI getObject() throws Exception {
        return APILocator.getPluginAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PluginAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
