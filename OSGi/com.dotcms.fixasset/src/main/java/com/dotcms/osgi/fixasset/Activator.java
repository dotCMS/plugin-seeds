package com.dotcms.osgi.fixasset;


import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.TaskLocatorUtil;
import org.osgi.framework.BundleContext;

/**
 * Created by Jonathan Gamba
 * Date: 6/18/12
 */
public class Activator extends GenericBundleActivator {


    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {


        System.out.println(Activator.class.getName() + " started");

        initializeServices ( context );

        TaskLocatorUtil.addFixTask(SampleFixTask.class);
    }

    public void stop ( BundleContext context ) throws Exception {
        TaskLocatorUtil.removeFixTask(SampleFixTask.class);
        unregisterServices( context );


    }

}