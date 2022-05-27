package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.htmlpageasset.business.render.HTMLPageAssetRenderedAPI;
import org.springframework.beans.factory.FactoryBean;

public class HTMLPageAssetRenderedAPIFactoryBean implements FactoryBean<HTMLPageAssetRenderedAPI> {

    @Override
    public HTMLPageAssetRenderedAPI getObject() throws Exception {
        return APILocator.getHTMLPageAssetRenderedAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return HTMLPageAssetRenderedAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
