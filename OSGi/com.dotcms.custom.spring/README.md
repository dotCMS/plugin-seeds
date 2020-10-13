# README
### This plugin uses the HTTP Servlet Service that is no longer supported in dotCMS as of version 5.1.6.  The code needs to be updated to use a Web Inteceptor rather than rely on registering a servlet as a spring controller.

This bundle plugin is an example of how to add Spring support to a bundle plugin, creates and registers a simple Spring Controller using a different Spring version that the one shipped with dotCMS in order to extend the reach of this example.

## How to build this example

To install all you need to do is build the JAR. to do this run
`./gradlew jar`

This will build two jars in the `build/libs` directory: a bundle fragment (in order to expose needed 3rd party libraries from dotCMS) and the plugin jar 

* **To install this bundle:**

    Copy the bundle jar files inside the Felix OSGI container (*dotCMS/felix/load*).
        
    OR
        
    Upload the bundle jars files using the dotCMS UI (*CMS Admin->Dynamic Plugins->Upload Plugin*).

* **To uninstall this bundle:**
    
    Remove the bundle jars files from the Felix OSGI container (*dotCMS/felix/load*).

    OR

    Undeploy the bundle jars using the dotCMS UI (*CMS Admin->Dynamic Plugins->Undeploy*).

## How to create a bundle plugin with Spring support

In order to create this OSGI plugin, you must create a `META-INF/MANIFEST` to be inserted into OSGI jar.
This file is being created for you by Gradle. If you need you can alter our config for this but in general our out of the box config should work.
The Gradle plugin uses BND to generate the Manifest. The main reason you need to alter the config is when you need to exclude a package you are including on your Bundle-ClassPath

If you are building the MANIFEST on your own or desire more info on it below is a description of what is required in this MANIFEST you must specify (see template plugin):

```
    Bundle-Name: The name of your bundle
    Bundle-SymbolicName: A short an unique name for the bundle
    Bundle-Activator: Package and name of your Activator class (example: com.dotmarketing.osgi.custom.spring.Activator)
    Bundle-ClassPath: The Bundle-ClassPath specifies where to load classes and jars from from the bundle.
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

### com.dotmarketing.osgi.custom.spring.ExampleController

Simple annotated Spring Controller

### com.dotmarketing.osgi.custom.spring.CustomViewResolver

Custom implementation of an Spring ViewResolver.

### com.dotmarketing.osgi.custom.spring.CustomView

Custom implementation of an Spring View.

### example-servlet.xml

Inside the *com.dotcms.custom.spring/src/main/resources/spring* folder is an Standard Spring configuration file where basically we enabled the support for anntotation-driven controllers and the Spring component-scan functionality.

### Activator

This bundle activator extends from *com.dotmarketing.osgi.GenericBundleActivator* and implements `BundleActivator.start()`.
Will manually register making use of the class *DispatcherServlet* our spring configuration file *spring/example-servlet.xml*.

* PLEASE note the `publishBundleServices( context )` call, this call is MANDATORY (!) as it will allow us to share resources between the bundle, the host container (dotCMS) and the Spring context.

---
## Testing

The Spring controller is registered under the url pattern **"/spring"** can be test it running and assuming your dotCMS url is *localhost:8080*:

* [http://localhost:8080/app/spring/examplecontroller/](http://localhost:8080/app/spring/examplecontroller/)
* [http://localhost:8080/app/spring/examplecontroller/Testing](http://localhost:8080/app/spring/examplecontroller/Testing)
