package com.dotcms.api.spring.factory;

import com.dotcms.enterprise.cluster.action.business.ServerActionAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ServerActionAPIFactoryBean implements FactoryBean<ServerActionAPI> {

    @Override
    public ServerActionAPI getObject() throws Exception {
        return APILocator.getServerActionAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ServerActionAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
