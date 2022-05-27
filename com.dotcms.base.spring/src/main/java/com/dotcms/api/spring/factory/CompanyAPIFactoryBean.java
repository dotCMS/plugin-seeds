package com.dotcms.api.spring.factory;

import com.dotcms.company.CompanyAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class CompanyAPIFactoryBean implements FactoryBean<CompanyAPI> {

    @Override
    public CompanyAPI getObject() throws Exception {
        return APILocator.getCompanyAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return CompanyAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
