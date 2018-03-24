#!/bin/bash

WORKSPACE=$(cd `dirname $0`; pwd)
cd $WORKSPACE

echo "Using JAVA_HOME:       $JAVA_HOME"
echo "Using MAVEN_HOME:      $MAVEN_HOME"
echo "Using WORKSPACE:       $WORKSPACE"

./mvnw -U clean install &&
./mvnw -U -pl noah-web jetty:run
