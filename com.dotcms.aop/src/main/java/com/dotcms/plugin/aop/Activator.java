package com.dotcms.plugin.aop;

import com.dotcms.plugin.aop.rest.MyContentResource;
import com.dotcms.rest.config.RestServiceUtil;
import org.osgi.framework.BundleContext;
import com.dotmarketing.osgi.GenericBundleActivator;

public class Activator extends GenericBundleActivator {

    Class clazz = MyContentResource.class;

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        //Initializing services...
        initializeServices ( context );
        System.out.println("Adding the rest: " + clazz);
        RestServiceUtil.addResource(clazz);
    }

    public void stop ( BundleContext context ) throws Exception {

        //Unregister all the bundle services
        System.out.println("Stoping the rest: " + clazz);
        RestServiceUtil.removeResource(clazz);
    }

}