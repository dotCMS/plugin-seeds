# plugin-seeds - The seed repo for dotCMS plugins and customizations

This project contains examples of how OSGi plugins can extend and customize dotCMS.

## Plugin Table of Contents

| Plugin Folder | What It Does |
|---|---|
| [`com.dotcms.3rd.party`](com.dotcms.3rd.party) | Demonstrates how to package and use a third-party Java library inside a dotCMS OSGi bundle. |
| [`com.dotcms.actionlet`](com.dotcms.actionlet) | Demonstrates how to add a custom Workflow Actionlet to dotCMS through an OSGi bundle. |
| [`com.dotcms.aop`](com.dotcms.aop) | Demonstrates how to build OSGi REST APIs in dotCMS with AOP annotations for timing, transaction handling, and DB-session cleanup. |
| [`com.dotcms.app.example`](com.dotcms.app.example) | Demonstrates how to create a dotCMS App-backed plugin with secure configuration, request-time usage, and config-change listeners. |
| [`com.dotcms.content.validation`](com.dotcms.content.validation) | Demonstrates workflow-time content validation using a custom Workflow Actionlet. |
| [`com.dotcms.contenttype`](com.dotcms.contenttype) | Demonstrates creating and removing a custom Content Type programmatically from an OSGi bundle. |
| [`com.dotcms.dynamic.skeleton`](com.dotcms.dynamic.skeleton) | Provides a minimal skeleton for building dotCMS OSGi plugins with the standard activator lifecycle. |
| [`com.dotcms.fixasset`](com.dotcms.fixasset) | Demonstrates registering a custom CMS Maintenance Fix Task through OSGi. |
| [`com.dotcms.hooks`](com.dotcms.hooks) | Demonstrates how to attach pre and post Contentlet API hooks via OSGi. |
| [`com.dotcms.hooks.pubsub`](com.dotcms.hooks.pubsub) | Demonstrates publishing cluster-aware Pub/Sub events from a content publish hook. |
| [`com.dotcms.hooks.validations`](com.dotcms.hooks.validations) | Demonstrates pre-checkin validation hooks using a pluggable strategy pattern. |
| [`com.dotcms.job`](com.dotcms.job) | Demonstrates scheduling Quartz jobs from an OSGi bundle in dotCMS. |
| [`com.dotcms.override`](com.dotcms.override) | Demonstrates overriding an existing dotCMS class implementation from an OSGi bundle. |
| [`com.dotcms.portlet`](com.dotcms.portlet) | Demonstrates adding custom admin portlets to dotCMS via OSGi. |
| [`com.dotcms.pushpublish.listener`](com.dotcms.pushpublish.listener) | Demonstrates listening to Push Publish lifecycle events in dotCMS. |
| [`com.dotcms.rest`](com.dotcms.rest) | Demonstrates exposing custom Jersey REST endpoints from a dotCMS OSGi bundle. |
| [`com.dotcms.ruleengine.velocityscriptingactionlet`](com.dotcms.ruleengine.velocityscriptingactionlet) | Adds a Rules Engine actionlet that executes Velocity script during rule evaluation. |
| [`com.dotcms.ruleengine.visitoripconditionlet`](com.dotcms.ruleengine.visitoripconditionlet) | Adds a Rules Engine conditionlet that evaluates visitor IP address against IP/CIDR criteria. |
| [`com.dotcms.servlet`](com.dotcms.servlet) | Clarifies the recommended approach for servlet/filter-style behavior in dotCMS plugins. |
| [`com.dotcms.simpleService`](com.dotcms.simpleService) | Demonstrates publishing a reusable OSGi service for consumption by other bundles. |
| [`com.dotcms.staticpublish.listener`](com.dotcms.staticpublish.listener) | Demonstrates extending static push publishing with an event-driven file transfer workflow. |
| [`com.dotcms.tuckey`](com.dotcms.tuckey) | Demonstrates registering Tuckey URL rewrite, redirect, and forward rules from an OSGi bundle. |
| [`com.dotcms.viewtool`](com.dotcms.viewtool) | Demonstrates creating and registering a custom Velocity ViewTool for dotCMS templates. |
| [`com.dotcms.webinterceptor`](com.dotcms.webinterceptor) | Demonstrates request/response interception patterns in dotCMS using WebInterceptors. |

## Build

All example plugins use Maven.

From any plugin folder:

```sh
mvn clean jar
```

## Getting Started

```sh
git clone https://github.com/dotCMS/plugin-seeds.git
cd plugin-seeds
```

## Documentation

- [dotCMS Plugins Documentation](https://dotcms.com/docs/latest/plugins)
- [dotCMS Public Plugin Repository](https://github.com/dotcms-plugins)
