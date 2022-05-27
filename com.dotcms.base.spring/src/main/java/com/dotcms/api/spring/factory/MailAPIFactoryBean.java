package com.dotcms.api.spring.factory;

import com.dotcms.mail.MailAPI;
import com.dotmarketing.business.APILocator;
import org.springframework.beans.factory.FactoryBean;

public class MailAPIFactoryBean implements FactoryBean<MailAPI> {

    @Override
    public MailAPI getObject() throws Exception {
        return APILocator.getMailApi();
    }

    @Override
    public Class<?> getObjectType() {
        return MailAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
