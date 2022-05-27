package com.dotcms.api.spring.factory;

import com.dotcms.cluster.business.ServerAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class ServerAPIFactoryBean implements FactoryBean<ServerAPI> {

    @Override
    public ServerAPI getObject() throws Exception {
        return APILocator.getServerAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ServerAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
