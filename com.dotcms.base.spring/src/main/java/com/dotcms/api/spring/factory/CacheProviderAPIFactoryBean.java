package com.dotcms.api.spring.factory;

import com.dotcms.enterprise.cache.provider.CacheProviderAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class CacheProviderAPIFactoryBean implements FactoryBean<CacheProviderAPI> {

    @Override
    public CacheProviderAPI getObject() throws Exception {
        return APILocator.getCacheProviderAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return CacheProviderAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
