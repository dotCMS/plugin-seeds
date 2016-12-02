
# README
------

This plugin allows to add an Rules Engine Actionlet (VelocityScriptingActionlet) that allow users to execute 
arbitrary velocity code in a rule.  Using this, an admin can leverage the power of Velocity and work directly with the request, response and session objects.

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


## How to Use


Once the plugin is installed, then :

1) Go to the Rule Engine portlet
2) Add or modify a rule
3) Add this VelocityScriptingActionlet actionlet passing the following parameter:
    3.1) and the velocity script code to execute.

Note: If The velocity script uses quotes or single quotes, those should be escaped to avoid a validation error on the rule engine. The quotes can be replaced with this values:

1) For single quotes (‘) replace with ${singleQuote}
2) For double quotes (“) replace with ${quote}

for example if you velocity code is:
```
<ul> #foreach($con in $dotcontent.pull(“+contentType:News”, 2, “random”)) <li>$con.title</li> #end </ul>
```
then should pass this code:
```
<ul> #foreach($con in $dotcontent.pull(${quote}+contentType:News${quote}, 2, ${quote}random${quote})) <li>$con.title</li> #end </ul>
```
