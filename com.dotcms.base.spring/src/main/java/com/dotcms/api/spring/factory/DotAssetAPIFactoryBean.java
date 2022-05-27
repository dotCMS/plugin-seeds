package com.dotcms.api.spring.factory;

import com.dotcms.contenttype.business.DotAssetAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class DotAssetAPIFactoryBean implements FactoryBean<DotAssetAPI> {

    @Override
    public DotAssetAPI getObject() throws Exception {
        return APILocator.getDotAssetAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return DotAssetAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
