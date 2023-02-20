#!/bin/bash

# Move to the root directory of the Maven project
cd /path/to/maven/project/

# Run Maven to package the project as a JAR file
mvn package

# Move to the target directory
cd target/

# Get the name of the JAR file
JAR_FILE=$(ls *.jar)

# Run the JAR file
java -jar $JAR_FILE
