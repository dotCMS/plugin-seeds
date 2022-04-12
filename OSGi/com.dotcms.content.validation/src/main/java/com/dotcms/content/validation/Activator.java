package com.dotcms.content.validation;

import org.osgi.framework.BundleContext;
import com.dotmarketing.osgi.GenericBundleActivator;

public class Activator extends GenericBundleActivator {



    @Override
    public void start ( BundleContext bundleContext ) throws Exception {



        //Initializing services...
        initializeServices( bundleContext );

        //Registering the test Actionlet
        registerActionlet( bundleContext, new ValidateContentlet() );
    }

    public void stop(BundleContext context) throws Exception {

        //Unregister all the bundle services
        unregisterServices(context);

    }

}