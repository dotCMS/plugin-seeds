package com.dotmarketing.osgi.webinterceptors;

import org.osgi.framework.BundleContext;
import com.dotcms.filters.interceptor.FilterWebInterceptorProvider;
import com.dotcms.filters.interceptor.WebInterceptorDelegate;
import com.dotmarketing.filters.InterceptorFilter;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Config;

public class Activator extends GenericBundleActivator {


    private String interceptorName;

    public void start(BundleContext context) throws Exception {

        final FilterWebInterceptorProvider provider =
                        FilterWebInterceptorProvider.getInstance(Config.CONTEXT);

        final WebInterceptorDelegate delegate = provider.getDelegate(InterceptorFilter.class);

        final PreWebInterceptor preWebInterceptor = new PreWebInterceptor();
        this.interceptorName = preWebInterceptor.getName();
        delegate.addFirst(preWebInterceptor);


    }

    public void stop(BundleContext context) throws Exception {

        final FilterWebInterceptorProvider provider =
                        FilterWebInterceptorProvider.getInstance(Config.CONTEXT);

        final WebInterceptorDelegate delegate = provider.getDelegate(InterceptorFilter.class);

        delegate.remove(this.interceptorName, true);

    }

}
