package com.dotcms.api.spring.factory;

import com.dotcms.rest.api.v1.system.websocket.WebSocketContainerAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class WebSocketContainerAPIFactoryBean implements FactoryBean<WebSocketContainerAPI> {

    @Override
    public WebSocketContainerAPI getObject() throws Exception {
        return APILocator.getWebSocketContainerAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return WebSocketContainerAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
