package com.dotcms.api.spring.factory;

import com.dotcms.api.tree.TreeableAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class TreeableAPIFactoryBean implements FactoryBean<TreeableAPI> {

    @Override
    public TreeableAPI getObject() throws Exception {
        return APILocator.getTreeableAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return TreeableAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
