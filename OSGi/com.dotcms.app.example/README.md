# dotCMS Example App
------
![Screen Shot 2020-11-02 at 12 43 55 PM](https://user-images.githubusercontent.com/934364/97900775-26ee6180-1d09-11eb-85f5-4a2724202b13.png)


This bundle plugin an OSGI example of how to create and use custom "Apps" built for dotCMS.  dotCMS Apps provide a secure store for plugin and system configuration and gives a dotCMS user the ability to configure custom dotCMS functionality.  While this example is simple, Apps can be combined with osgi based custom REST endpoints, portlets, workflows, content hooks and servlets/filters to provide advanced and custom functionality.

- This plugin adds a new `dotAppExample.yml` to the dotCMS app defintion folder.  You can see the example .yaml here:  https://github.com/dotCMS/plugin-seeds/blob/master/OSGi/com.dotcms.app.example/src/main/resources/dotAppExample.yml

- This plugin also adds a new `WebInterceptor` that logs your app configuration - if available - on every request. 

- Finally, this plugin registers an App listener that can be used to respond to changes in your App's configuration - to validate a config or do other external work.

Secrets and App config stored in Apps can be accessed using code as displayed below - which will return the App configuration of the current host, if available, or fall back and return the app configuration for the `SYSTEM_HOST`, if available. In order to access the App.getSecrets, the user must be a CMS_Admin or have access to the App portlet.

```java
        
        final Host host = WebAPILocator.getHostWebAPI().getCurrentHostNoThrow(request);
        Optional<AppSecrets> appSecrets = APILocator.getAppsAPI().getSecrets(AppKeys.APP_KEY, true, host, APILocator.systemUser());
        return appSecrets
        
```


How to build this example
-------------------------

To build this, run  `./gradlew jar`.  This will create two jars, both of which you should upload into your dotCMS.


