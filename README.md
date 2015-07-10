spring-mvc 
===

[![Build Status](https://travis-ci.org/mycoin/spring-mvc.svg?branch=master)](https://travis-ci.org/mycoin/spring-mvc)

This project will allow you to start off on the good foot with spring MVC.


### Get start
---
Clone the repository:

```
git clone git@github.com:mycoin/spring-mvc.git
mvn jetty:run
```

or 

Import the project as a maven project into your IDE of choice. 
Run the class `org.ionnic.app.Main`

then open <http://localhost:8080/spring-mvc/>


###Overview
---

This is an application to demo the implementation of a REST API that can return responses in both JSON and XML. 
There are three ways of requesting a specific type of response:

1. Setting the Accept header to `application/[json|xml]`
2. Adding a `?format=[json|xml]` parameter to the url.
3. Adding the format as the file type of the request. Eg instead of the url `http://localhost:8090/object`, your would have `http://localhost:8090/object.json`

URLs the servlet responds to:

+ `/hello` - responds with the text Hello.
+ `/welcome` - Either a get or post request will work. If there is a name parameter in the request, the value of the name parameter will be included in the welcome response. 
+ `/secure` - This path will only respond to GET requests. The response format can be changed from the default of json to xml by using one of the 
	three methods listed above. The response is a description of a person object in json or xml.  
	The /people* urls require basic authentication to access them. This is set up in the security context. The user name is `admin` and the password is `12345`.


### Features

* Uses embedded Jetty
* Uses basic auth to secure some url paths
* A simple RESTful api
* Uses Spring annotations
* Uses HttpMessageConverters - allows the serialization of objects to XML and JSON. Also used for converting request bodies into Java objects.
* Security configuration isolated to a single file.
* Comments with explanations of why some features were implemented a certain way.

### HttpMessageConverters

HttpMessageConverters is the magic that provides automatic conversion of Java objects to XML and JSON. In the rest-demo-servlet.xml, the 
section starting with `<mvc:annotation-driven>` automatically creates some standard HttpMessageConverters based on what jars are on the classpath. In particular, if the 
Jackson library is on the classpath, then a JSON converter will be created. We also add an XML marshaller based on XStream. We do this manually because by default Spring doesn't seem to do it quite right.


### Main.java

This is the class that runs an embedded Jetty server with the servlet. 


TODO
====

* Examples of testing Spring Controllers