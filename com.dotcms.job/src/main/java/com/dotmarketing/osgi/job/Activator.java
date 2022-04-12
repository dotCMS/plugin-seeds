package com.dotmarketing.osgi.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.osgi.framework.BundleContext;
import org.quartz.CronTrigger;
import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.quartz.CronScheduledTask;
import com.dotmarketing.servlets.InitServlet;
import com.dotmarketing.util.Logger;
import com.google.common.collect.ImmutableList;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public class Activator extends GenericBundleActivator {

    public final static String JOB_NAME = "Custom Job";
    public final static String JOB_CLASS = "com.dotmarketing.osgi.job.CustomJob";
    public final static String JOB_GROUP = "User Jobs";

    public final static String CRON_EXPRESSION = "0/10 * * * * ?";// Every 10 seconds

    private LoggerContext pluginLoggerContext;

    private final List<Class> overriddenClasses = ImmutableList.of(CustomJob.class, TestClass.class);
    private final ClassLoader webappClassLoader = InitServlet.class.getClassLoader();
    private final ClassLoader bundleClassLoader = this.getClass().getClassLoader();


    private ClassReloadingStrategy classReloadingStrategy = ClassReloadingStrategy.fromInstalledAgent();

    @SuppressWarnings("unchecked")
    public void start(BundleContext context) throws Exception {

        // Initializing log4j...
        LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();
        // Initialing the log4j context of this plugin based on the dotCMS logger context
        pluginLoggerContext = (LoggerContext) LogManager.getContext(this.getClass().getClassLoader(), false,
                        dotcmsLoggerContext, dotcmsLoggerContext.getConfigLocation());

        // Initializing services...
        initializeServices(context);
        injectClassesIntoDotCMS();
        // Job params
        Map<String, Object> params = new HashMap<>();
        params.put("param1", "value1");
        params.put("param2", "value2");

        // Creating our custom Quartz Job
        CronScheduledTask cronScheduledTask = new CronScheduledTask(JOB_NAME, JOB_GROUP, JOB_NAME, JOB_CLASS,
                        new Date(), null, CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW, params, CRON_EXPRESSION);

        // Schedule our custom job
        scheduleQuartzJob(cronScheduledTask);
    }

    public void stop(BundleContext context) throws Exception {
        // removes the classes from the classloader
        removeClassesFromDotCMS();

        // Unregister all the bundle services
        unregisterServices(context);

        // Shutting down log4j in order to avoid memory leaks
        Log4jUtil.shutdown(pluginLoggerContext);
    }

    /**
     * Will inject this bundle context code inside the dotCMS context
     *
     * @param className a reference class inside this bundle jar
     * @throws Exception
     */
    protected void injectClassesIntoDotCMS() throws Exception {

        for (Class clazz : overriddenClasses) {


            Logger.info(this.getClass().getName(),
                            "Injecting: " + clazz.getName() + " into classloader: " + webappClassLoader);
            Logger.debug(this.getClass().getName(), "bundle classloader :" + bundleClassLoader);
            Logger.debug(this.getClass().getName(), "dotCMS classloader:" + webappClassLoader);

            ByteBuddyAgent.install();
            new ByteBuddy().rebase(clazz, ClassFileLocator.ForClassLoader.of(bundleClassLoader)).name(clazz.getName())
                            .make().load(webappClassLoader, classReloadingStrategy);

        }
    }


    /**
     * Will remove the overridden class from the dotCMS Classloader
     *
     * @param className a reference class inside this bundle jar
     * @throws Exception
     */
    protected void removeClassesFromDotCMS() throws Exception {


        for (Class clazz : overriddenClasses) {
            Logger.info(this.getClass().getName(),
                            "Removing: " + clazz.getName() + " from classloader: " + webappClassLoader);
            try {
                this.classReloadingStrategy.reset(ClassFileLocator.ForClassLoader.of(webappClassLoader), clazz);
            } catch (Exception e) {
                Logger.debug(this.getClass(),
                                String.format("Error resetting [%s] class in dotCMS classloader", clazz.getName()));
            }


        }
    }


}
