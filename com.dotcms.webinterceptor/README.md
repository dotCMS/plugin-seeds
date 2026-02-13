# com.dotcms.webinterceptor

## Purpose
This plugin demonstrates request/response interception patterns in dotCMS using WebInterceptors.

## What This Plugin Does
- Registers `PreWebInterceptor` for `/app/helloworld` to demonstrate break, rewrite, and redirect behaviors.
- Registers `WrappingWebInterceptor` for `/*` to wrap requests/responses and add headers/attributes.
- Adds and removes interceptors through the interceptor delegate lifecycle.

## When a Customer Might Use This
- You need filter-like logic without writing raw servlet filters.
- You want centralized header management, URL rewrites, or request decoration.
- You need endpoint-specific interception with plugin deployment control.

There are 2 WebInterceptors in this example.  

### PreWebInterceptor - a Simple Filter/Servlet
The first, called PreWebInterceptor, only responses to the url pattern `/app/helloworld`.   You can test it by hitting `https://localhost:8443/app/helloworld`

If it is installed correctly, it will print a hello message.  It takes an "action" parameter.
    
* https://localhost:8443/app/helloworld?action=break
this will throw a 400 response and stop processing

* https://localhost:8443/app/helloworld?action=rewrite
This will transparently rewrite the request from `/app/helloworld` to `/`.  It will not redirect the user's browser.

* https://localhost:8443/app/helloworld?action=redirect
This will send a 302 redirect to the user's browser, redirecting them to `/` 

### WrappingWebInterceptor - Wrapping the request and response and sending it on in the chain.

The second, called `WrappingWebInterceptor`, responds to all requests, e.g. `/*` and  will wrap the incoming request and response and add request.attributes to the request and specific headers to the response.  You can hit the endpoint and it should print the fake attributes.  It will also "pass" the request on to be processed.  You can see this in action by curling any page or request to the system and checking the included headers:
```
curl --head -k https://127.0.0.1:8443/app/helloworld

HTTP/1.1 200
Strict-Transport-Security: max-age=3600;includeSubDomains
X-Frame-Options: SAMEORIGIN
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Access-Control-Allow-Origin: http://dotcms.webinterceptor.com  <------------------------------
X-WEBINTERCEPTOR: WrappingWebInterceptor                       <------------------------------
Transfer-Encoding: chunked
Date: Wed, 09 Mar 2022 14:50:55 GMT

```


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
