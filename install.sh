#!/bin/bash
mvn clean install
cd noah-web && mvn jetty:run