
## Setting properties in Maven projects ##

A sample implementaion of the a Mojo-based plug-in that can be used to share values maven-varibales (properties) between different projects. The properies are supposed to be packed resource files in JAR-artifacts. The plug-in installs found properties as maven variables

The implementation is the project *PropertiesPlugin*. The projects *PropertiesContainer* and *PropertiesUsage* are used to test the plug-in.

# How-To #

1. Install this project from maven. The tests are executed automatically.
2. The project *PropertiesPlugin* has JUnit-test. Run it from Eclipse to see ho it works. 
3. Run the maven-test for project *PropertiesUsage*. This project show "real" usage of the plug-in

 