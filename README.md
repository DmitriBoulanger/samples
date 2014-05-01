Samples Repository
==================

This repository contains Java code-samples that are used in the current Java-developer World.
The repository is organized as a Maven-project with the single top (this Maven-project).

###Maven features
This Maven-project is the top-level reactor. It fixes the most basic properties for 
all samples in this repository:

- java.version 
- slf4j.version 
- spring.version 
- junit.version
	
and the encoding as UTF-8

###Important hints
- logger resource-itself is **only** available within the test-scope. This ensures that
the main code is not dependent on any logger-resource.  However, 
the Log4j-Logger is used in most of the tests. This logger is activated 
in the test-resources with the  **log4.properties** file.

