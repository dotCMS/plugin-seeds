package com.dotcms.api.spring.factory;

import com.dotcms.storage.FileStorageAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class FileStorageAPIFactoryBean implements FactoryBean<FileStorageAPI> {

    @Override
    public FileStorageAPI getObject() throws Exception {
        return APILocator.getFileStorageAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FileStorageAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
