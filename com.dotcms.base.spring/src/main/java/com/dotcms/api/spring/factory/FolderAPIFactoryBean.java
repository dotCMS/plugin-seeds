package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.folders.business.FolderAPI;
import org.springframework.beans.factory.FactoryBean;

public class FolderAPIFactoryBean implements FactoryBean<FolderAPI> {

    @Override
    public FolderAPI getObject() throws Exception {
        return APILocator.getFolderAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FolderAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
