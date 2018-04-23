package com.dotmarketing.osgi.spring;

import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import org.apache.felix.http.api.ExtHttpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by Jonathan Gamba
 * Date: 6/18/12
 */
public class Activator extends GenericBundleActivator {

    private LoggerContext pluginLoggerContext;

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        //Initializing log4j...
        LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();
        //Initialing the log4j context of this plugin based on the dotCMS logger context
        pluginLoggerContext = (LoggerContext) LogManager
                .getContext(this.getClass().getClassLoader(),
                        false,
                        dotcmsLoggerContext,
                        dotcmsLoggerContext.getConfigLocation());

        //Initializing services...
        initializeServices( context );

        //Getting the current thread class loader
        ClassLoader currentContextClassLoader = Thread.currentThread().getContextClassLoader();

        //Service reference to ExtHttpService that will allows to register servlets and filters
        ServiceReference sRef = context.getServiceReference( ExtHttpService.class.getName() );
        if ( sRef != null ) {

            //Publish bundle services
            publishBundleServices( context );

            ExtHttpService httpService = (ExtHttpService) context.getService( sRef );
            try {

                /*
                 Work around for problems loading classes using third-party libraries in an OSGi bundle.
                 The way that third-party libraries load their classes is out of your control.
                 In most cases works properly, if the third-party library uses the normal classloader from the bundle.
                 But sometimes a library, like Spring could try to load a class like the following:
                    Thread.currentThread().getContextClassLoader().loadClass("mypackage.FooBar");
                 By default, the Thread context class loader is not aware of OSGi and
                 as result doesn't see any of the classes imported in the bundle. That's why loading the class fails.
                 References:
                    https://helpx.adobe.com/experience-manager/kb/OsgiClassLoading3Party.html
                    https://stackoverflow.com/questions/2198928/better-handling-of-thread-context-classloader-in-osgi
                 */
                Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

                DispatcherServlet dispatcherServlet = new DispatcherServlet();
                dispatcherServlet.setContextConfigLocation( "spring/example-servlet.xml" );
                httpService.registerServlet( "/spring", dispatcherServlet, null, null );
            } catch ( Exception e ) {
                e.printStackTrace();
            } finally {
                //Setting back the original class loader to the current thread
                Thread.currentThread().setContextClassLoader(currentContextClassLoader);
            }

            CMSFilter.addExclude( "/app/spring" );
        }
    }

    public void stop ( BundleContext context ) throws Exception {

        CMSFilter.removeExclude( "/app/spring" );

        //Unpublish bundle services
        unregisterServices( context );

        //Shutting down log4j in order to avoid memory leaks
        Log4jUtil.shutdown(pluginLoggerContext);
    }

}