package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.workflows.business.WorkflowAPI;
import org.springframework.beans.factory.FactoryBean;

public class WorkflowAPIFactoryBean implements FactoryBean<WorkflowAPI> {

    @Override
    public WorkflowAPI getObject() throws Exception {
        return APILocator.getWorkflowAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return WorkflowAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
