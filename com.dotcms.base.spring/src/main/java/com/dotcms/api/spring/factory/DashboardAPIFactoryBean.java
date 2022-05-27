package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.dashboard.business.DashboardAPI;
import org.springframework.beans.factory.FactoryBean;

public class DashboardAPIFactoryBean implements FactoryBean<DashboardAPI> {

    @Override
    public DashboardAPI getObject() throws Exception {
        return APILocator.getDashboardAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return DashboardAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
