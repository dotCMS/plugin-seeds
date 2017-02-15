package com.dotmarketing.osgi.custom.spring;

import com.dotcms.repackage.org.directwebremoting.servlet.DwrServlet;
import org.apache.felix.http.api.ExtHttpService;
import org.apache.felix.http.whiteboard.HttpWhiteboardConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.osgi.GenericBundleActivator;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Jonathan Gamba
 *         Date: 6/18/12
 */
public class Activator extends GenericBundleActivator {

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        System.out.println("Doing init for spring example osgi");
        //Initializing services...
        initializeServices( context );

        //Service reference to ExtHttpService that will allows to register servlets and filters
        System.out.println("Getting the HttpService");

            System.out.println("Publish services");
            //Publish bundle services
            publishBundleServices( context );//This call will make the elements of the bundle, like our controller class available to the Spring context

            try {
                DispatcherServlet dispatcherServlet = new DispatcherServlet();
                dispatcherServlet.setContextConfigLocation( "spring/example-servlet.xml" );

                System.out.println("Registering the dispatcher service");
                final Dictionary<String, Object> servlet2Props = new Hashtable<String, Object>();
                servlet2Props.put(HttpWhiteboardConstants.PATTERN, "/spring");
                servlet2Props.put("osgi.http.whiteboard.servlet.pattern", "/spring");
                servlet2Props.put("servlet.init.message", "Spring Example!");
                context.registerService(DispatcherServlet.class, dispatcherServlet, servlet2Props);
                System.out.println("Registering the dispatcher service DONE");
            } catch ( Exception e ) {
                e.printStackTrace();
            }
//            ExtHttpService httpService = (ExtHttpService) context.getService( sRef );
//            try {
//
//                System.out.println("Registering the dispatcher service");
//                DispatcherServlet dispatcherServlet = new DispatcherServlet();
//                dispatcherServlet.setContextConfigLocation( "spring/example-servlet.xml" );
//                httpService.registerServlet( "/spring", dispatcherServlet, null, null );
//                System.out.println("Registering the dispatcher service DONE");
//            } catch ( Exception e ) {
//                e.printStackTrace();
//            }
            CMSFilter.addExclude( "/app/spring" );
    }

    public void stop ( BundleContext context ) throws Exception {

        CMSFilter.removeExclude( "/app/spring" );

        //Unpublish bundle services
        unregisterServices( context );
    }

}