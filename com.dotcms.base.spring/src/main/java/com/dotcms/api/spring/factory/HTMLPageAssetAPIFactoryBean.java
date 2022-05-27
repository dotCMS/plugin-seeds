package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.htmlpageasset.business.HTMLPageAssetAPI;
import org.springframework.beans.factory.FactoryBean;

public class HTMLPageAssetAPIFactoryBean implements FactoryBean<HTMLPageAssetAPI> {

    @Override
    public HTMLPageAssetAPI getObject() throws Exception {
        return APILocator.getHTMLPageAssetAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return HTMLPageAssetAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
