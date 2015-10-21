#!/bin/bash
mvn -U eclipse:clean eclipse:eclipse -DdownloadSources=true
mvn -U clean install -DdownloadSources=true 
