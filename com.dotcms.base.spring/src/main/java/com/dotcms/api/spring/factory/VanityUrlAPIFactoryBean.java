package com.dotcms.api.spring.factory;

import com.dotcms.browser.BrowserAPI;
import com.dotcms.vanityurl.business.VanityUrlAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class VanityUrlAPIFactoryBean implements FactoryBean<VanityUrlAPI> {

    @Override
    public VanityUrlAPI getObject() throws Exception {
        return APILocator.getVanityUrlAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return VanityUrlAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
