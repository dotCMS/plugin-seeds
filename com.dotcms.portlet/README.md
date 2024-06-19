# README

This bundle plugin is an example of how to create and add Portlets for use in the dotCMS' administrative console using an OSGI bundle plugin.
This example includes the code to create three different types of Portlets, all of which are supported by dotCMS:
* **Velocity Portlet**: a Portlet implementation that will display portlet content using a velocity file
* **Jsp Portlet**: a Portlet implementation that will display portlet content using a jsp file
* **Struts Portlet**: a Portlet implementation  that will call an Struts Action class that will use a jsp file in order to display portlet content.

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

## How to create a bundle plugin for Portlets

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

### Resources:

* **conf/** Folder (Folder that contains the configuration files for the Portlets definitions) - Both files in this folder are MANDATORY (!)
    * **conf/portlet.xml** The standard JSR-286 portlet configuration file.
    * **conf/liferay-portlet.xml** This file describes some optional Liferay-specific enhancements for JSR-286 portlets that are installed on a Liferay Portal server.

* **ext/** Folder (Folder that contains the files used by the defined Portles)
    * **ext/view.vtl** Velocity file used by the Velocity Portlet
    * **ext/hello.jsp** Jsp file use it by the Jsp Portlet
    * **ext/strutshelloworld/view.jsp** Used by the Struts Portlet and invoked inside the *HelloWorldAction*
    * **ext/strutshelloworld/view_hello.jsp** Used by the Struts Portlet and invoked inside the *HelloWorldAction*

### com.dotmarketing.osgi.portlet.HelloWorldAction

Simple Action class that extends *com.dotmarketing.portal.struts.DotPortletAction*
The *conf/portlet.xml* file has the definition for an *StrutsPortlet* and that definition has a reference to the mapping for the *HelloWorldAction*.

### Activator

This bundle activator extends *com.dotmarketing.osgi.GenericBundleActivator* and implements *BundleActivator.start()*.
This activator have 2 main important fragments of code:
* It will manually register an *ActionMapping* for our Struts action class *HelloWorldAction* that will be used by the StrutsPortlet we defined in the configuration files (*conf/*).
* It will manually register the Portlets making use of the method *registerPortlets*.

**PLEASE note** the *unregisterServices()* call on the *stop* method, this call is MANDATORY (!) as it will allow us to clean and remove the registered Portlets and related code (like the ActionMappings registered in Struts  ).

### Multi language support

The creation of the Portlets will generate the following language keys and use them as the title for the Portlets:
* javax.portlet.title.EXT_HELLO_WORLD
* javax.portlet.title.EXT_JSP_HELLO_WORLD
* javax.portlet.title.EXT_STRUTS_HELLO_WORLD

In order to add multilanguage values for those keys:
*CMS Admin* -> *Language Variables* -> *Edit Default Language Variables* -> Add the values for the above keys
  	
