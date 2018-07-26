package com.dotcms.plugin.actionlet;

import com.dotmarketing.osgi.GenericBundleActivator;
import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator {

    public Activator () {}

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        //Initializing services...
        initializeServices ( context );

        registerActionlet( context, new SavePublishContentActionlet() );
    }

    public void stop ( BundleContext context ) throws Exception {

        unregisterActionlets();
    }


}

