package com.dotmarketing.osgi.actionlet;

import com.dotcms.util.AnnotationUtils;
import com.dotcms.util.BundleUrlType;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.osgi.GenericBundleActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleWiring;

public class Activator extends GenericBundleActivator {


    @Override
    public void start (final BundleContext bundleContext ) throws Exception {

        //Initializing services...
        initializeServices( bundleContext );

        final Bundle bundle                  = bundleContext.getBundle();
        final BundleWiring bundleWiring      = bundle.adapt(BundleWiring.class);
        final ClassLoader  classLoader       = bundleWiring.getClassLoader();
        final String basePackage             = "com.dotmarketing.osgi.actionlet.actionlets";

        AnnotationUtils.addUrlType(new BundleUrlType(bundle));

        APILocator.getWorkflowAPI().scanPackageForActionlets(basePackage, classLoader);
    }

    public void stop(BundleContext context) throws Exception {

        //Unregister all the bundle services
        unregisterServices(context);
    }

}
