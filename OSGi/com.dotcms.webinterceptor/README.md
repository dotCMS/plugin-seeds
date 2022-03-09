# README

This bundle plugin is an example of how to use WebInterceptors.  WebInterceptors respond to one or more URIs (they accept * at the end of their pattern) and can be used to:

* Act as a `javax.servlet.Filter`
* Wrap the incoming request and the outgoing response to provide headers, or custom functionality
* Act as a `javax.servlet.Servlet`, responding to specific URIs

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

To install all you need to do is build the JAR. to do this run
`./gradlew clean jar`

This will build two jars in the `build/libs` directory: a bundle fragment (in order to expose needed 3rd party libraries from dotCMS) and the plugin jar 

* **To install this bundle:**

    Upload the bundle jars files using the dotCMS UI (*CMS Admin->Dynamic Plugins->Upload Plugin*).

* **To uninstall this bundle:**
    
    Undeploy the bundle jars using the dotCMS UI (*CMS Admin->Dynamic Plugins->Undeploy*).
