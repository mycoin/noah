#!/bin/bash

echo "Using JAVA_HOME:       $JAVA_HOME"
echo "Using MAVEN_HOME:      $MAVEN_HOME\n"

mvn -U eclipse:clean eclipse:eclipse -DdownloadSources=true
mvn -U clean install -DdownloadSources=true
