# com.dotcms.app.example

## Purpose
This plugin demonstrates how to create a dotCMS App-backed plugin with secure configuration, request-time usage, and config-change listeners.

## What This Plugin Does
- Installs `dotAppExample.yml` into the server app definitions folder on startup.
- Registers a web interceptor that reads app secrets/config and logs the resolved values per request.
- Subscribes to app secret save events so plugin code can react when app configuration changes.

## When a Customer Might Use This
- You need secure, host-aware plugin configuration managed through dotCMS Apps.
- You want plugin behavior to change immediately when admins update app settings.
- You need a reference for combining Apps, event subscribers, and interceptors in one plugin.

Secrets and App config stored in Apps can be accessed using code as displayed below - which will return the App configuration of the current host, if available, or fall back and return the app configuration for the `SYSTEM_HOST`, if available. In order to access the App.getSecrets, the user must be a CMS_Admin or have access to the App portlet.

```java

        final Host host = WebAPILocator.getHostWebAPI().getCurrentHostNoThrow(request);
        Optional<AppSecrets> appSecrets = APILocator.getAppsAPI().getSecrets(AppKeys.APP_KEY, true, host, APILocator.systemUser());
        return appSecrets

```

How to build this example
-------------------------

To build this, run  `mvn clean install`.  This will create a jar which you should upload into your dotCMS.

