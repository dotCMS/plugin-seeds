package com.dotcms.api.spring.factory;

import com.dotcms.util.FileWatcherAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class FileWatcherAPIFactoryBean implements FactoryBean<FileWatcherAPI> {

    @Override
    public FileWatcherAPI getObject() throws Exception {
        return APILocator.getFileWatcherAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FileWatcherAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
