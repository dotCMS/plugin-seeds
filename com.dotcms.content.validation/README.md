# README

This plugin is an example of how you can add custom validation to content using a WorkFlowActionlet.  The plugin supplies a new WorkflowActionlet called "Content Validation Actionlet Example" that can be added to workflow tasks.  The validation it uses is simple and intended as an example - it will not let you save a piece of content with "nosave" it its title, nor will it allow you to publish a piece of content with "nopublish" in its title. 

## How to build this example

To install all you need to do is build the JAR. to do this run
`mvn clean install`

This will build two jars in the `build/libs` directory: a bundle fragment (in order to expose needed 3rd party libraries from dotCMS) and the plugin jar 

* **To install this bundle:**

    Copy the bundle jar files inside the Felix OSGI container (*dotCMS/felix/load*).
        
    OR
        
    Upload the bundle jars files using the dotCMS UI (*Admin->Plugins->Upload Plugin*).

* **To uninstall this bundle:**
    
    Remove the bundle jars files from the Felix OSGI container (*dotCMS/felix/load*).

    OR

    Undeploy the bundle jars using the dotCMS UI (*CMS Admin->Dynamic Plugins->Undeploy*).

