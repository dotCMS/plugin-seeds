package com.dotcms.visitor.character;

import com.dotcms.visitor.filter.logger.VisitorLogger;
import org.osgi.framework.BundleContext;
import com.dotmarketing.osgi.GenericBundleActivator;

/**
 * @author nollymar
 * Date: 5/16/18
 */
public class Activator extends GenericBundleActivator {

    @SuppressWarnings ("unchecked")
    public void start ( BundleContext context ) throws Exception {
        VisitorLogger.addConstructor(CustomCharacter.class);
    }

    public void stop ( BundleContext context ) throws Exception {
        VisitorLogger.removeConstructor(CustomCharacter.class);
    }

}