# README

This bundle plugin is an example of how to add dotcms content hooks via OSGi. 
Content hooks works like interceptors and can be called before content has been modified (pre-hooks) or after the content has been checked in (post-hooks).

This particular example works such as blueprint for a prehook over the validateContentlet method (on the ContentletAPI), you can use this example as a reference in order to extends by type or types 
the custom validations via OSGI plugin 

In the example the hook is called ValidatorPreContentHook it basically use a set of ValidatorStrategies to do the validations, you can see in the 
Activator how three Validator are being passed to the Hook instance.

ValidatorStrategy
This interface encapsulates the signature for a validator, it basically has a test that will let it know to the hook if the validator could be or not applied to the contentlet, usually we will ask for a 
content type variable or base content type here, if the test returns true the hook will invokes the applyValidation in order to do the actual validation.

In our example we have three of them, all are doing similar things, basically make a test over the title field of each contentlet, asking for specific words such as ('generic', 'base') or if starts with a digit. 
You can use them just as a reference to create your own ones or just modified them to align with your validations needs. 


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

## How to create a bundle plugin for dotCMS hook classes

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

### com.dotmarketing.osgi.hooks.SamplePostContentHook AND com.dotmarketing.osgi.hooks.ValidatorPreContentHook

Hooks classes that will override the contentletCount method to see how they work.

### Activator

This bundle activator extends from com.dotmarketing.osgi.GenericBundleActivator and implements BundleActivator.start().
Calls ContentletAPI.contentletCount() who will fire ours hooks methods.
