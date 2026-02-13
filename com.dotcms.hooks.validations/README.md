# com.dotcms.hooks.validations

## Purpose
This plugin demonstrates pre-checkin validation hooks using a pluggable strategy pattern.

## What This Plugin Does
- Registers `ValidatorPreContentHook` as a pre-content hook.
- Applies multiple `ValidatorStrategy` implementations based on content type/conditions.
- Throws validation exceptions for titles that do not satisfy sample strategy rules.

## When a Customer Might Use This
- You need reusable validation rules shared across multiple content types.
- You want richer validation than field-level constraints.
- You need a reference for composable hook-based validation logic.

ValidatorStrategy
This interface encapsulates the signature for a validator, it basically has a test that will let it know to the hook if the validator could be or not applied to the contentlet, usually we will ask for a 
content type variable or base content type here, if the test returns true the hook will invokes the applyValidation in order to do the actual validation.

In our example we have three of them, all are doing similar things, basically make a test over the title field of each contentlet, asking for specific words such as ('generic', 'base') or if starts with a digit. 
You can use them just as a reference to create your own ones or just modified them to align with your validations needs. 


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

---
## Components

### com.dotmarketing.osgi.hooks.SamplePostContentHook AND com.dotmarketing.osgi.hooks.ValidatorPreContentHook

Hooks classes that will override the contentletCount method to see how they work.

### Activator

This bundle activator extends from com.dotmarketing.osgi.GenericBundleActivator and implements BundleActivator.start().
Calls ContentletAPI.contentletCount() who will fire ours hooks methods.
