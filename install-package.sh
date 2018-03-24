#!/bin/bash

WORKSPACE=$(cd `dirname $0`; pwd)
cd $WORKSPACE
./mvnw -U clean install > /dev/null

java -jar ./noah-web/target/noah-web-0.0.1-SNAPSHOT-jar-executable.jar
