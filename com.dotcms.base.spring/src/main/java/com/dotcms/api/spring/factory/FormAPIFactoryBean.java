package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.form.business.FormAPI;
import org.springframework.beans.factory.FactoryBean;

public class FormAPIFactoryBean implements FactoryBean<FormAPI> {

    @Override
    public FormAPI getObject() throws Exception {
        return APILocator.getFormAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return FormAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
