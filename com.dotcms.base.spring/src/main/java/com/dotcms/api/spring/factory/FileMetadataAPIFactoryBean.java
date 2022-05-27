package com.dotcms.api.spring.factory;

import com.dotcms.storage.FileMetadataAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class FileMetadataAPIFactoryBean implements FactoryBean<FileMetadataAPI> {

    @Override
    public FileMetadataAPI getObject() throws Exception {
        return APILocator.getFileMetadataAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FileMetadataAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
