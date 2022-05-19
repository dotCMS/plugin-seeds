package com.dotmarketing.osgi.override;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.portlets.folders.model.Folder;
import com.dotmarketing.util.Logger;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.pool.TypePool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.osgi.framework.BundleContext;

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
        rebase();
        /*
         * Trying to use our custom implementation of this class, after the last method call should be possible
         * if was added the Override-Classes property inside the MANIFEST.MF.
         */
        Folder folder = new Folder();
        folder.getPath();
    }

    private void rebase () throws ClassNotFoundException {

        final String className = "com.dotmarketing.portlets.folders.model.Folder";
        try {
            ByteBuddyAgent.install();
            final ClassLoader classLoader = getBundleClassloader();
            final TypePool typePool = TypePool.Default.of(classLoader);
            final TypeDescription typeDescription = typePool.describe(className).resolve();
            final ClassFileLocator locator = ClassFileLocator.ForClassLoader.of(classLoader);
            final ByteBuddy byteBuddy = new ByteBuddy();
            final Class<?> loaded = byteBuddy
                    .rebase(typeDescription, locator)
                    .name(className)
                    .make()
                    .load(getWebAppClassloader(), this.getClassReloadingStrategy())
                    .getLoaded();
        } catch (Throwable e) {

            Logger.info(this.getClass().getName(), "Error trying to rebase, probably was already rebased: " + e.getMessage());
        }
    }

    public void stop ( BundleContext context ) throws Exception {

        //Unpublish bundle services
        unpublishBundleServices();

        //Shutting down log4j in order to avoid memory leaks
        Log4jUtil.shutdown(pluginLoggerContext);
    }

}
