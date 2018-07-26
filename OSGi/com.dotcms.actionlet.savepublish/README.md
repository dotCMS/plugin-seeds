
# README
----
This is an example of how to create a save and publish actionlet in dotCMS via OSGi and Annotation Framework based on AOP

On the build.gradle you can see the necessary configuration to apply our aspects on your plugin in order to be able to use 
the annotations in your project; currently dotCMS is using aspectjs as an AOP provider and compile-time injection for aspects.


## How to build this example
----

To install all you need to do is build the JAR. To do this run from this directory:

`./gradlew jar`

or for windows

`.\gradlew.bat jar`

This will build fragment and jar in the build/libs directory, first upload the fragment and then the jar.

### To install this bundle

Copy the bundle jar file inside the Felix OSGI container (dotCMS/felix/load).
        OR
Upload the bundle jar file using the dotCMS UI (CMS Admin->Dynamic Plugins->Upload Plugin).

### To uninstall this bundle:

Remove the bundle jar file from the Felix OSGI container (dotCMS/felix/load).
        OR
Undeploy the bundle using the dotCMS UI (CMS Admin->Dynamic Plugins->Undeploy).



## How to test
----

Once installed, you can add the actionlet to some action.
The action basically does a save and publish in one action.
Run the action in any Content type that has the parent scheme associated.
