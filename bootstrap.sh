#!/bin/bash

TOMCAT_HOME=/usr/local/opt/tomcat/7.0.52.0/
mvn clean
mvn install -Dmaven.test.skip=true


if [[ -d $TOMCAT_HOME/webapps/spring-mvc ]]; then
    rm -rf $TOMCAT_HOME/webapps/spring-mvc
    rm $TOMCAT_HOME/webapps/spring-mvc.war
fi

echo "copy target/spring-mvc-0.0.1-SNAPSHOT.war"
cp target/spring-mvc-0.0.1-SNAPSHOT.war $TOMCAT_HOME/webapps/spring-mvc.war

sh $TOMCAT_HOME/bin/catalina.sh run