
# Static Publish Plugin


This plugin enables the push publishing of static objects from a dotCMS sender to a remote server using SSH File Transfer Protocol (SFTP). It is done through a push publishing listener that is subscribed to an event `SingleStaticPublishEndpointSuccessEvent`, once this plugin is deployed on dotCMS as a dynamic plugin.

For further information about dotCMS Dynamic Plugins and Push Publishing Listeners, please visit:

* https://dotcms.com/docs/latest/osgi-plugins 
* https://dotcms.com/docs/latest/push-publish-listeners-configuration


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
* **remote.path**: absolute path where static content will be stored in the receiver

**Note:** The plugin needs to be built and redeployed as a dynamic plugin in dotCMS each time the `plugin.properties` file is modified



## How to build an OSGi project


To install, all you need to do is build the JAR. To do this, run `./gradlew jar`. This will build a jar in the build/libs directory. Refer to the build.gradle for more information.



1. To install this bundle:

Copy the bundle jar file inside the Felix OSGI container (`WEB-INF/felix/load`) OR Upload the bundle jar file using the dotCMS UI (`CMS Admin->Dynamic Plugins->Upload Plugin`).

2. To uninstall this bundle:

Remove the bundle jar file from the Felix OSGI container (`WEB-INF/felix/load`) OR Undeploy the bundle using the dotCMS UI (`CMS Admin->Dynamic Plugins->Undeploy`).

## Additional Notes

In order to create this OSGI plugin, you must create a `META-INF/MANIFEST` to be inserted into OSGI jar. This file is being created for you by Gradle. If you need to, you can alter our config for this, but our out of the box config should work. The Gradle plugin uses BND to generate the Manifest. The main reason you need to alter the config is when you need to exclude a package you are including on your Bundle-ClassPath

If you are building the MANIFEST on your own, or desire more info, below is a description of what is required in the MANIFEST that you must specify (see template plugin):

**Bundle-Name**: The name of your bundle

**Bundle-SymbolicName**: A short and unique name for the bundle

**Bundle-Activator**: Package and name of your Activator class (example: com.dotmarketing.osgi.viewtools.Activator)

**DynamicImport-Package**: Dynamically add required imports the plugin may need without add them explicitly

**Import-Package**: This is a comma separated list of package names. In this list there must be the packages that you are using inside the plugin bundle and that are exported during dotCMS runtime.
