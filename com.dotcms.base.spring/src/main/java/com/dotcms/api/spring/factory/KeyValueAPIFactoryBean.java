package com.dotcms.api.spring.factory;

import com.dotcms.keyvalue.business.KeyValueAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class KeyValueAPIFactoryBean implements FactoryBean<KeyValueAPI> {

    @Override
    public KeyValueAPI getObject() throws Exception {
        return APILocator.getKeyValueAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return KeyValueAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
