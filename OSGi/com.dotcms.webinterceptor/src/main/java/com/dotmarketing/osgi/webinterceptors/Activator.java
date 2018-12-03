package com.dotmarketing.osgi.webinterceptors;

import com.dotcms.filters.interceptor.FilterWebInterceptorProvider;
import com.dotcms.filters.interceptor.WebInterceptorDelegate;
import com.dotcms.filters.interceptor.cas.CasAutoLoginWebInterceptor;
import com.dotmarketing.filters.AutoLoginFilter;
import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Config;
import org.apache.felix.http.api.ExtHttpService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import javax.servlet.Servlet;

public class Activator extends GenericBundleActivator {

    private Servlet simpleServlet;
    private ExtHttpService httpService;
    private String interceptorName;

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        //Initializing services...
        initializeServices( context );

        //Service reference to ExtHttpService that will allows to register servlets and filters
        ServiceReference sRef = context.getServiceReference( ExtHttpService.class.getName() );

        if ( sRef != null ) {

            httpService = (ExtHttpService) context.getService( sRef );
            try {
                //Registering a simple test servlet
                simpleServlet = new HelloWorldServlet( );
                httpService.registerServlet( "/helloworld", simpleServlet, null, null );
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            final FilterWebInterceptorProvider filterWebInterceptorProvider =
                    FilterWebInterceptorProvider.getInstance(Config.CONTEXT);

            final WebInterceptorDelegate delegate =
                        filterWebInterceptorProvider.getDelegate(AutoLoginFilter.class);

            final PreWebInterceptor preWebInterceptor = new PreWebInterceptor();
            this.interceptorName = preWebInterceptor.getName();
            delegate.addFirst(preWebInterceptor);
        }

        CMSFilter.addExclude( "/app/helloworld" );
    }

    public void stop ( BundleContext context ) throws Exception {

        //Unregister the servlet
        if ( httpService != null && simpleServlet != null ) {
            httpService.unregisterServlet( simpleServlet );
        }

        final FilterWebInterceptorProvider filterWebInterceptorProvider =
                FilterWebInterceptorProvider.getInstance(Config.CONTEXT);

        final WebInterceptorDelegate delegate =
                filterWebInterceptorProvider.getDelegate(AutoLoginFilter.class);

        delegate.remove(this.interceptorName, true);

        CMSFilter.removeExclude( "/app/helloworld" );
    }

}