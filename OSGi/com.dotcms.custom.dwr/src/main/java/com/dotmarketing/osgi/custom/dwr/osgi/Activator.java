package com.dotmarketing.osgi.custom.dwr.osgi;

import com.dotcms.repackage.org.directwebremoting.servlet.DwrServlet;
import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.osgi.GenericBundleActivator;
import org.apache.felix.http.whiteboard.HttpWhiteboardConstants;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Nathan Keiter
 *         Date: 12/05/13
 */
public class Activator extends GenericBundleActivator {

    private DwrServlet dwrServlet;
    //private ExtHttpService extHttpService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void start ( BundleContext context ) throws Exception {

        //Initializing services...
        System.out.println( "Starting Dwr Custom Ajax Example");

        initializeServices( context );

        System.out.println( "Init done for Dwr Custom Ajax Example");

        //Service reference to ExtHttpService that allows us to register servlets and filters
        //ServiceReference serviceReference = context.getServiceReference( ExtHttpService.class.getName() );

        //if ( serviceReference != null ) {

            System.out.println("Going to publish the services");
            //Publish bundle services
            publishBundleServices( context );

            //Load http service extension object from service reference
            // extHttpService = (ExtHttpService) context.getService( serviceReference );

            //Create our DwrServlet instance
            dwrServlet = new DwrServlet();

            System.out.println("Registering the DWR Servlet");
            //Register our DwrServlet
            // extHttpService.registerServlet( "/custom_dwr", dwrServlet, null, null );
            final Dictionary<String, Object> servlet2Props = new Hashtable<String, Object>();
            servlet2Props.put(HttpWhiteboardConstants.PATTERN, "/custom_dwr");
            servlet2Props.put("osgi.http.whiteboard.servlet.pattern", "/custom_dwr");
            servlet2Props.put("servlet.init.message", "DWR Example!");
            context.registerService(DwrServlet.class, dwrServlet, servlet2Props);
        //}

        //Add servlet path to CMS exclusion list
        CMSFilter.addExclude( "/app/custom_dwr" );
    }

    public void stop ( BundleContext context ) throws Exception {

        //Unregister all the bundle services
        unregisterServices( context );
    }

}