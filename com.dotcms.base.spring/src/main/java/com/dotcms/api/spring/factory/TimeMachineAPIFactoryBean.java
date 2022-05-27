package com.dotcms.api.spring.factory;

import com.dotcms.timemachine.business.TimeMachineAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class TimeMachineAPIFactoryBean implements FactoryBean<TimeMachineAPI> {

    @Override
    public TimeMachineAPI getObject() throws Exception {
        return APILocator.getTimeMachineAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return TimeMachineAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
