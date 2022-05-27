package com.dotcms.api.spring.factory;

import com.dotcms.graphql.business.GraphqlAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class GraphqlAPIFactoryBean implements FactoryBean<GraphqlAPI> {

    @Override
    public GraphqlAPI getObject() throws Exception {
        return APILocator.getGraphqlAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return GraphqlAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
