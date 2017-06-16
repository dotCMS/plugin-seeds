package com.dotcms.tuckey;

import org.osgi.framework.BundleContext;

import com.dotcms.repackage.org.tuckey.web.filters.urlrewrite.Condition;
import com.dotcms.repackage.org.tuckey.web.filters.urlrewrite.NormalRule;
import com.dotcms.repackage.org.tuckey.web.filters.urlrewrite.SetAttribute;
import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.osgi.GenericBundleActivator;

public class Activator extends GenericBundleActivator {

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {

        //Initializing services...
        initializeServices( context );
        
        
        //Register a simple tuckey redirect rule
        addRewriteRule( "^/example/redirect/(.*)$", "/about-us/", "redirect", "ExampleTuckeyRedirect" );

        //Register a simple tuckey rewrite rule
        addRewriteRule( "^/example/rewrite/(.*)$", "/about-us/$1", "redirect", "ExampleTuckeyRewrite" );


        
        //Creating a conditional rule
        NormalRule conditionRule = new NormalRule();
        conditionRule.setFrom( "^/example/condition/(.*)$" );
        conditionRule.setToType( "redirect" );
        conditionRule.setTo( "/about-us/?browser=chrome" );
        conditionRule.setName( "ExampleTuckeyCondition" );
        
        //Create a Condition for this rule
        Condition condition = new Condition();
        condition.setName( "user-agent" );
        condition.setValue( "Chrome/*.*" );
        conditionRule.addCondition( condition );

        //Register the tuckey rewrite rule
        addRewriteRule( conditionRule );
        
        
        
        // Forwarding calls a RequestDispatcher, which short-circuits any use
        // of the CMSFilter that maps uris to pages and files for front end traffic
        // This means that it is only possible to tuckey forward to
        // servlets or non-dotcms served pages
        
        addRewriteRule( "^/example/forwardTuckey/(.*)$", "/dA/249eeb5c-7002", "forward", "ExampleTuckeyForward" );

        
        
        // It is still possible to forward to dotCMS pages or urls though.  What you need to do
        // is set the request Parameter CMSFilter.CMS_FILTER_URI_OVERRIDE.  This allows you
        // to specify a forwarding page in dotCMS
        
        NormalRule forwardRule = new NormalRule();
        forwardRule.setFrom( "^/example/forwardDotCMS/(.*)$" );
        SetAttribute attribute = new SetAttribute();
        attribute.setName(CMSFilter.CMS_FILTER_URI_OVERRIDE);
        attribute.setValue("/about-us/index");
        forwardRule.addSetAttribute(attribute);
        addRewriteRule( forwardRule );
        
        
        
        
        
        
        
    }

    public void stop ( BundleContext context ) throws Exception {

        //Unregister all the bundle services
        unregisterServices( context );
    }

}