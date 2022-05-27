package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.hostvariable.bussiness.HostVariableAPI;
import org.springframework.beans.factory.FactoryBean;

public class HostVariableAPIFactoryBean implements FactoryBean<HostVariableAPI> {

    @Override
    public HostVariableAPI getObject() throws Exception {
        return APILocator.getHostVariableAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return HostVariableAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
