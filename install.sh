#!/bin/bash
#!/bin/bash

echo "Using JAVA_HOME:       $JAVA_HOME"
echo "Using MAVEN_HOME:      $MAVEN_HOME"
echo "Using MAVEN_OPTS:      $MAVEN_OPTS\n"

mvn -U clean install -DdownloadSources=true

cd app
mvn jetty:run
