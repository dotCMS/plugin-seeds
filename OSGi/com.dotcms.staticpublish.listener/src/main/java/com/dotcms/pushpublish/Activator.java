package com.dotcms.pushpublish;

import com.dotcms.pushpublish.listener.SuccessEndpointsSubscriber;
import com.dotcms.system.event.local.business.LocalSystemEventsAPI;
import com.dotcms.system.event.local.type.staticpublish.AllStaticPublishEndpointsSuccessEvent;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.osgi.GenericBundleActivator;

import org.osgi.framework.BundleContext;


/**
 * This class will be used by OSGi as the entry point to your code during the bundle
 * activation.
 */
public class Activator extends GenericBundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        //Initializing services...
        initializeServices(bundleContext);
        final LocalSystemEventsAPI localSystemEventsAPI =
            APILocator.getLocalSystemEventsAPI();

        //Subscribing to all endpoints success event
        SuccessEndpointsSubscriber successEndpointsSubscriber = new SuccessEndpointsSubscriber();
        localSystemEventsAPI.subscribe(AllStaticPublishEndpointsSuccessEvent.class, successEndpointsSubscriber);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // remember to "unregister" anything you registered at the "start" method
        // this will ensure the working consistency of the system.
        final LocalSystemEventsAPI localSystemEventsAPI =
            APILocator.getLocalSystemEventsAPI();

        //Important: unsubscribe events
        localSystemEventsAPI
            .unsubscribe(AllStaticPublishEndpointsSuccessEvent.class, SuccessEndpointsSubscriber.class.getName());

    }
} // EOC Activator