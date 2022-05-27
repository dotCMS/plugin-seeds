package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.categories.business.CategoryAPI;
import com.dotmarketing.portlets.widget.business.WidgetAPI;
import org.springframework.beans.factory.FactoryBean;

public class WidgetAPIFactoryBean implements FactoryBean<WidgetAPI> {

    @Override
    public WidgetAPI getObject() throws Exception {
        return APILocator.getWidgetAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return WidgetAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
