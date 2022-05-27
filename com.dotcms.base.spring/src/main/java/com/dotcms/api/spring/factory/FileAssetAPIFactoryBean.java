package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.fileassets.business.FileAssetAPI;
import org.springframework.beans.factory.FactoryBean;

public class FileAssetAPIFactoryBean implements FactoryBean<FileAssetAPI> {

    @Override
    public FileAssetAPI getObject() throws Exception {
        return APILocator.getFileAssetAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FileAssetAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
