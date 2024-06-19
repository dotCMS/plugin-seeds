# README

This bundle plugin is an example of how to Schedule Quartz Jobs using an OSGI bundle plugin.  It is an important example because it also demonstrates how to inject classes from your bundle into the running dotCMS instance.  The plugin activator has two methods that add and remove the class definitions to and from the dotCMS class loader. This is important because Quartz needs cleanly instantiate the Job and Test classes when it runs in the dotCMS context - outside of the bundle. 

## How to build this example

To install all you need to do is build the JAR. to do this run
`mvn clean install`

This will build two jars in the `build/libs` directory: a bundle fragment (in order to expose needed 3rd party libraries from dotCMS) and the plugin jar 

* **To install this bundle:**

    Copy the bundle jar files inside the Felix OSGI container (*dotCMS/felix/load*).
        
    OR
        
    Upload the bundle jars files using the dotCMS UI (*CMS Admin->Dynamic Plugins->Upload Plugin*).

* **To uninstall this bundle:**
    
    Remove the bundle jars files from the Felix OSGI container (*dotCMS/felix/load*).

    OR

    Undeploy the bundle jars using the dotCMS UI (*CMS Admin->Dynamic Plugins->Undeploy*).

## How to create a bundle plugin for Schedule Quartz Jobs

In order to create this OSGI plugin, you must create a `META-INF/MANIFEST` to be inserted into OSGI jar.
This file is being created for you by Gradle. If you need you can alter our config for this but in general our out of the box config should work.
The Gradle plugin uses BND to generate the Manifest. The main reason you need to alter the config is when you need to exclude a package you are including on your Bundle-ClassPath

If you are building the MANIFEST on your own or desire more info on it below is a description of what is required in this MANIFEST you must specify (see template plugin):

```
    Bundle-Name: The name of your bundle
    Bundle-SymbolicName: A short an unique name for the bundle
    Bundle-Activator: Package and name of your Activator class (example: com.dotmarketing.osgi.override.Activator)
    Export-Package: Declares the packages that are visible outside the plugin. Any package not declared here has visibility only within the bundle.
    Import-Package: This is a comma separated list of the names of packages to import. In this list there must be the packages that you are using inside your osgi bundle plugin and are exported and exposed by the dotCMS runtime.
```

## Beware (!)

In order to work inside the Apache Felix OSGI runtime, the import and export directive must be bidirectional, there are two ways to accomplish this:

* **Exported Packages**

    The dotCMS must declare the set of packages that will be available to the OSGI plugins by changing the file: *dotCMS/WEB-INF/felix/osgi-extra.conf*.
This is possible also using the dotCMS UI (*CMS Admin->Dynamic Plugins->Exported Packages*).

    Only after that exported packages are defined in this list, a plugin can Import the packages to use them inside the OSGI blundle.
    
* **Fragment**

    A Bundle fragment, is a bundle whose contents are made available to another bundles exporting 3rd party libraries from dotCMS.
One notable difference is that fragments do not participate in the lifecycle of the bundle, and therefore cannot have an Bundle-Activator.
As it not contain a Bundle-Activator a fragment cannot be started so after deploy it will have its state as Resolved and NOT as Active as a normal bundle plugin.

---
## Components

### com.dotmarketing.osgi.job.CustomJob

Simple Job class that implements the regular Quartz Job interface

### Activator

This bundle activator extends from *com.dotmarketing.osgi.GenericBundleActivator* and implements *BundleActivator.start()*.
Will manually register a *CronScheduledTask* making use of the method *scheduleQuartzJob*

* PLEASE note the *unregisterServices()* call on the *stop* method, this call is MANDATORY (!) as it will allow us to stop and
remove the register Quartz Job when the plugin is undeploy.

---

# Limitations (!)

There are limitations on the hot deploy functionality for the OSGI Quartz Job plugin, once you upload this plugin you are limited
on what code you can modify for the Quartz Job classes in order to see those changes the next time you upload the plugin.

This will apply only for the OSGI Quartz plugins, exactly to the Quartz Job class you implement and the classes use it by it, because
in order to integrate this plugin with the dotCMS/Quartz code we are using our plugin code outside the OSGI and the plugin context,
trying to let know to dotCMS/Quartz that there is a Job outside its classpath trying to be use it and instantiate by them.

In order to support the use of the Quartz Jobs inside ours OSGI plugins we use the java hot swapping, it allows to redefine classes,
unfortunately, this redefinition is limited only to changing method bodies:

> The redefinition may change method bodies, the constant pool and attributes. The redefinition must not add, remove or rename fields or methods, change the signatures of methods, or change inheritance.

As long as you don't add, remove or change methods (ONLY the methods bodies) for your Job code you will have an OSGI plugin that
will reflect you changes when a redeploy is done. If you need to change the signature of the Job classes a restart of the dotCMS app will be require.