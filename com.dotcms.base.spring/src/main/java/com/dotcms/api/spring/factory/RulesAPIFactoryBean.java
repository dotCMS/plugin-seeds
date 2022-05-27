package com.dotcms.api.spring.factory;

import com.dotcms.enterprise.rules.RulesAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class RulesAPIFactoryBean implements FactoryBean<RulesAPI> {

    @Override
    public RulesAPI getObject() throws Exception {
        return APILocator.getRulesAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return RulesAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
