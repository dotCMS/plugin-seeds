package com.dotcms.pushpublish;

import com.dotcms.api.system.event.local.type.AddedToQueueEvent;
import com.dotcms.api.system.event.local.type.AllEndpointsSuccessEvent;
import com.dotcms.pushpublish.listener.AddToQueueSubscriber;
import com.dotcms.pushpublish.listener.PushPublishEndSubscriber;
import com.dotcms.pushpublish.listener.PushPublishStartSubscriber;
import com.dotcms.pushpublish.listener.SuccessEndpointsSubscriber;
import org.osgi.framework.BundleContext;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotcms.api.system.event.local.LocalSystemEventsAPI;
import com.dotmarketing.business.APILocator;


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

        //Subscribing to a publishing queue event
        AddToQueueSubscriber queueSubscriber = new AddToQueueSubscriber();
        localSystemEventsAPI.subscribe(AddedToQueueEvent.class, queueSubscriber);

        //Subscribing to push publish start
        PushPublishStartSubscriber startSubscriber = new PushPublishStartSubscriber();
        localSystemEventsAPI.subscribe(startSubscriber);

        //Subscribing to push publish end
        PushPublishEndSubscriber endSubscriber = new PushPublishEndSubscriber();
        localSystemEventsAPI.subscribe(endSubscriber);

        //Subscribing to all endpoints success event
        SuccessEndpointsSubscriber successEndpointsSubscriber = new SuccessEndpointsSubscriber();
        localSystemEventsAPI.subscribe(AllEndpointsSuccessEvent.class, successEndpointsSubscriber);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // remember to "unregister" anything you registered at the "start" method
        // this will ensure the working consistency of the system.
    }
} // EOC Activator