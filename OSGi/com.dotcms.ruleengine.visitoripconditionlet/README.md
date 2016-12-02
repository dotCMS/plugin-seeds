# README

This plugin allow to add a Conditionlet (VisitorIPConditionlet) to validate if the client IP matches or does not match an IP or a specific subnet.  It takes either a single standard IP(v4) like `192.168.1.45` or a whole subnet using CIDR notation, e.g. `192.168.1.1/24`

**Note:** This plugin requires to add in the startup.sh or dotStartup.sh the following JAVA_OPTS parameter.
If you are using tomcat to retrieve the IP's with IPv4 format. `JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"`

## How to build this example

To install all you need to do is build the JAR. to do this run
```./gradlew jar```
This will build a jar in the build/libs directory

1. To install this bundle:

Copy the bundle jar file inside the Felix OSGI container (dotCMS/felix/load).
        OR
Upload the bundle jar file using the dotCMS UI (CMS Admin->Dynamic Plugins->Upload Plugin).

2. To uninstall this bundle:

Remove the bundle jar file from the Felix OSGI container (dotCMS/felix/load).
        OR
Undeploy the bundle using the dotCMS UI (CMS Admin->Dynamic Plugins->Undeploy).






