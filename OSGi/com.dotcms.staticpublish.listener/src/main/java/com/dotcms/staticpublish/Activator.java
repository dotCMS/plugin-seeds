package com.dotcms.staticpublish;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;
import com.dotcms.staticpublish.listener.SuccessEndpointsSubscriber;
import com.dotcms.system.event.local.business.LocalSystemEventsAPI;
import com.dotcms.system.event.local.type.staticpublish.SingleStaticPublishEndpointSuccessEvent;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;

import org.osgi.framework.BundleContext;


/**
 * This class will be used by OSGi as the entry point to your code during the bundle
 * activation.
 */
public class Activator extends GenericBundleActivator {

    private LoggerContext pluginLoggerContext;

    @Override
    public void start(BundleContext bundleContext) throws Exception {

//        //Initializing log4j...
        LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();
//        //Initialing the log4j context of this plugin based on the dotCMS logger context
        pluginLoggerContext = (LoggerContext) LogManager
            .getContext(this.getClass().getClassLoader(),
               false,
                dotcmsLoggerContext,
                dotcmsLoggerContext.getConfigLocation());

        //Initializing services...
        initializeServices(bundleContext);
        final LocalSystemEventsAPI localSystemEventsAPI =
            APILocator.getLocalSystemEventsAPI();

        //Subscribing to all endpoints success event
        SuccessEndpointsSubscriber successEndpointsSubscriber = new SuccessEndpointsSubscriber();
        localSystemEventsAPI.subscribe(SingleStaticPublishEndpointSuccessEvent.class, successEndpointsSubscriber);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // remember to "unregister" anything you registered at the "start" method
        // this will ensure the working consistency of the system.
        final LocalSystemEventsAPI localSystemEventsAPI =
            APILocator.getLocalSystemEventsAPI();

        //Important: unsubscribe events
        localSystemEventsAPI
            .unsubscribe(SingleStaticPublishEndpointSuccessEvent.class, SuccessEndpointsSubscriber.class.getName());

        unregisterServices( bundleContext );

        //Shutting down log4j in order to avoid memory leaks
        Log4jUtil.shutdown(pluginLoggerContext);
    }
} // EOC Activator