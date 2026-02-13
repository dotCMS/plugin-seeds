# com.dotcms.servlet

## Purpose
This example clarifies the recommended approach for servlet/filter-style behavior in dotCMS plugins.

## What This Plugin Does
- Documents that WebInterceptors are the supported OSGi pattern instead of raw servlet/filter registration.
- Points implementers to the dedicated web interceptor example plugin.

## When a Customer Might Use This
- You are planning a servlet/filter customization and need the supported extension path.
- You want to avoid implementing against unsupported servlet registration patterns in OSGi.
