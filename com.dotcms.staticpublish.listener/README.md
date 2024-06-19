# README

This plugin push publishing of static objects from a dotCMS sender to a remote server using SSH File Transfer Protocol (SFTP). It is done through a push publishing listener that is subscribed to an event `SingleStaticPublishEndpointSuccessEvent`, once this plugin is deployed on dotCMS as a dynamic plugin.

You need to create a Static Endpoint, after the bundle is successfully saved locally is going to try to transfer the files to the SFTP server.

For further information about dotCMS Dynamic Plugins and Push Publishing Listeners, please visit:

* https://dotcms.com/docs/latest/osgi-plugins 
* https://dotcms.com/docs/latest/push-publish-listeners-configuration

## How to build this example

To build the JAR, run the following Maven command: 
```sh
mvn clean install
```

This will generate the plugin JAR in the `target` directory.

## How to install this bundle

* **To install this bundle:**

  Copy the bundle JAR file inside the Felix OSGI container (`dotCMS/felix/load`).

  OR

  Upload the bundle JAR file using the dotCMS UI (`CMS Admin -> Plugins -> Upload Plugin`).

* **To uninstall this bundle:**

  Remove the bundle JAR file from the Felix OSGI container (`dotCMS/felix/load`).

  OR

  Undeploy the bundle JAR using the dotCMS UI (`CMS Admin -> Plugins -> Undeploy`).

## How to create a bundle plugin for Annotation Framework based on AOP  

In order to create this OSGI plugin, Maven is configured to generate the `META-INF/MANIFEST.MF` file automatically. If needed, you can customize the configuration in the `pom.xml`.

Below is a description of the required fields in the `MANIFEST.MF` and how they are configured in a `pom.xml`:

> **Bundle-Name:** The name of your bundle  
> **Bundle-SymbolicName:** A short and unique name for the bundle  
> **Bundle-Vendor:** The vendor of the bundle (example: dotCMS)  
> **Bundle-Description:** A brief description of the bundle  
> **Bundle-DocURL:** URL for the bundle documentation  
> **Bundle-Activator:** Package and name of your Activator class (example: com.dotmarketing.osgi.actionlet.Activator)  
> **Export-Package:** Declares the packages that are visible outside the plugin. Any package not declared here has visibility only within the bundle.  
> **Import-Package:** This is a comma-separated list of the names of packages to import. This list must include the packages that you are using inside your OSGI bundle plugin and are exported and exposed by the dotCMS runtime.

These fields are configured in the `pom.xml` as follows:

```xml
<plugin>
    <groupId>org.apache.felix</groupId>
    <artifactId>maven-bundle-plugin</artifactId>
    <version>5.1.9</version>
    <extensions>true</extensions>
    <configuration>
        <instructions>
            <Bundle-Name>Your Bundle Name</Bundle-Name>
            <Bundle-SymbolicName>com.example.yourbundle</Bundle-SymbolicName>
            <Bundle-Vendor>dotCMS</Bundle-Vendor>
            <Bundle-Description>dotCMS - OSGI Actionlet example</Bundle-Description>
            <Bundle-DocURL>https://dotcms.com/</Bundle-DocURL>
            <Bundle-Activator>com.dotmarketing.osgi.actionlet.Activator</Bundle-Activator>
            <Export-Package>com.example.yourbundle.package</Export-Package>
            <Import-Package>*</Import-Package>
        </instructions>
    </configuration>
</plugin>
```

## Beware (!)

In order to work inside the Apache Felix OSGI runtime, the import and export directives must be bidirectional:

* **Exported Packages**

  The dotCMS must declare the set of packages that will be available to the OSGI plugins by updating the file: `dotCMS/WEB-INF/felix/osgi-extra.conf`.
  This can also be configured using the dotCMS UI (`CMS Admin -> Plugins -> Exported Packages`).

  Only after the exported packages are defined in this list, can a plugin import the packages to use them inside the OSGI bundle.

* **Fragment (Deprecated)**

  Previously, a bundle fragment was used to make its contents available to other bundles by exporting 3rd party libraries from dotCMS. Fragments do not participate in the lifecycle of the bundle and therefore cannot have a Bundle-Activator. As this is no longer required, this section is deprecated.

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
