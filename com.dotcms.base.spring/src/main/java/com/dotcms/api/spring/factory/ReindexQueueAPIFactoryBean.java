package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.common.reindex.ReindexQueueAPI;
import org.springframework.beans.factory.FactoryBean;

public class ReindexQueueAPIFactoryBean implements FactoryBean<ReindexQueueAPI> {

    @Override
    public ReindexQueueAPI getObject() throws Exception {
        return APILocator.getReindexQueueAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return ReindexQueueAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
