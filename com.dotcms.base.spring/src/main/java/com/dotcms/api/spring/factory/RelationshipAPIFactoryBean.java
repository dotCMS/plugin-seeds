package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.RelationshipAPI;
import org.springframework.beans.factory.FactoryBean;

public class RelationshipAPIFactoryBean implements FactoryBean<RelationshipAPI> {

    @Override
    public RelationshipAPI getObject() throws Exception {
        return APILocator.getRelationshipAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return RelationshipAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
