package com.dotcms.api.spring.factory;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.personas.business.PersonaAPI;
import org.springframework.beans.factory.FactoryBean;

public class PersonaAPIFactoryBean implements FactoryBean<PersonaAPI> {

    @Override
    public PersonaAPI getObject() throws Exception {
        return APILocator.getPersonaAPI();
    }

    @Override
    public Class<?> getObjectType() {
        return PersonaAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
