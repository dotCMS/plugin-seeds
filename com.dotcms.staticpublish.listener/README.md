# README

This plugin push publishing of static objects from a dotCMS sender to a remote server using SSH File Transfer Protocol (SFTP). It is done through a push publishing listener that is subscribed to an event `SingleStaticPublishEndpointSuccessEvent`, once this plugin is deployed on dotCMS as a dynamic plugin.

You need to create a Static Endpoint, after the bundle is successfully saved locally is going to try to transfer the files to the SFTP server.

For further information about dotCMS Dynamic Plugins and Push Publishing Listeners, please visit:

* https://dotcms.com/docs/latest/osgi-plugins 
* https://dotcms.com/docs/latest/push-publish-listeners-configuration

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

## How to create a bundle plugin

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

## Important Notes:

1. Be aware that you need to subscribe and stop the listener under Activator's start and stop method respectively.
2. dotCMS needs to be installed on the sender under a Platform License in order to use the Static Publish feature. However, the receiver does not need to be a dotCMS instance.
3. We use sshj project in this example to establish the connection between sender and receiver. This library relies on a Bouncy Castle dependency in order to secure all requests. Bouncy Castle needs to be verified against Java Security Framework, in order to do that Bouncy Castle jars can't be part of this osgi plugin (see link). So we will be using dotCMS own Bouncy Castle library for this purpose. [http://side-effects-bang.blogspot.com/2015/02/deploying-uberjars-that-use-bouncy.html](http://side-effects-bang.blogspot.com/2015/02/deploying-uberjars-that-use-bouncy.html)

## How to Configure

All information required by the sender to establish a secure connection with a remote server needs to be included in the file `src/main/resources/plugin.properties`. 

The following key properties are required:

* **key.file.path**: absolute path (in the sender) where the .pem certificate to be used for the connection is located
* **ssh.user**: ssh user to be used to connect with the receiver
* **hosts**: receiver's ip address
* **host.port**: receiver's port (22 by default)
* **remote.path**: absolute path where static content will be stored in the receiver

**Note:** The plugin needs to be built and redeployed as a dynamic plugin in dotCMS each time the `plugin.properties` file is modified
