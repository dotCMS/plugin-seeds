package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.links.business.MenuLinkAPI;
import org.springframework.beans.factory.FactoryBean;

public class MenuLinkAPIFactoryBean implements FactoryBean<MenuLinkAPI> {

    @Override
    public MenuLinkAPI getObject() throws Exception {
        return APILocator.getMenuLinkAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return MenuLinkAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
