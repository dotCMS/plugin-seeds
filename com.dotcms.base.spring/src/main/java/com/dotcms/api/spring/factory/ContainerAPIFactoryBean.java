package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.containers.business.ContainerAPI;
import org.springframework.beans.factory.FactoryBean;

public class ContainerAPIFactoryBean implements FactoryBean<ContainerAPI> {

    @Override
    public ContainerAPI getObject() throws Exception {
        return APILocator.getContainerAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ContainerAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
