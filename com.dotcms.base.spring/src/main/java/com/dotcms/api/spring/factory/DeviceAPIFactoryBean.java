package com.dotcms.api.spring.factory;

import com.dotcms.device.DeviceAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class DeviceAPIFactoryBean implements FactoryBean<DeviceAPI> {

    @Override
    public DeviceAPI getObject() throws Exception {
        return APILocator.getDeviceAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return DeviceAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
