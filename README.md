spring-mvc
========

This project will allow you to start off on the good foot with spring MVC.


### To get the code:
-------------------
Clone the repository:

```
git clone git@github.com:mycoin/spring-mvc.git
```

If this is your first time using Github, review http://help.github.com to learn the basics.

###To run the application:
-------------------
Import the project as a maven project into your IDE of choice. 
Look for the `com.spidertracks.demo.rest.api.Start` class in src/test/java and run it. You should now have the application running at http://localhost:8090/rest/


Spring Framework Version: 3.1.0.RELEASE


###Overview
========

This is an application to demo the implementation of a REST API that can return responses in both JSON and XML. 
There are three ways of requesting a specific type of response:

1. Setting the Accept header to `application/[json|xml]`
2. Adding a `?format=[json|xml]` parameter to the url.
3. Adding the format as the file type of the request. Eg instead of the url `http://localhost:8090/object`, your would have `http://localhost:8090/object.json`

URLs the servlet responds to:

+ `/hello` - responds with the text Hello.
+ `/welcome` - Either a get or post request will work. If there is a name parameter in the request, the value of the name parameter will be 
		included in the welcome response. 
+ `/people` - This path will only respond to GET requests. The response format can be changed from the default of json to xml by using one of the 
	three methods listed above. The response is a description of a person object in json or xml.  
	The /people* urls require basic authentication to access them. This is set up in the security context. The user name is `user` and the password is `pass`.

	Example URLs:

		http://localhost:8090/rest/people/search?name=doe
		http://localhost:8090/rest/people/search.xml?name=doe
		http://localhost:8090/rest/people/search.json?name=doe
		http://localhost:8090/rest/people
		http://localhost:8090/rest/people.json
		http://localhost:8090/rest/people.xml
		http://localhost:8090/rest/people/1
		http://localhost:8090/rest/people/1.xml
		http://localhost:8090/rest/people/1.json
		http://localhost:8090/rest/people - Use POST with a body and appropriate content type to create a new person.
		http://localhost:8090/rest/people/1 - with PUT request and a updated person object
		http://localhost:8090/rest/people/1.xml  - with PUT request and a updated person object    
		http://localhost:8090/rest/people/1.json  - with PUT request and a updated person object
		http://localhost:8090/rest/people/1 - DELETE request
		http://localhost:8090/rest/people/1.json - DELETE request
		http://localhost:8090/rest/people/1.xml - DELETE request

### Features

* Uses embedded Jetty
* Uses basic auth to secure some url paths
* A simple RESTful api
* Uses Spring annotations
* Uses HttpMessageConverters - allows the serialization of objects to XML and JSON. Also used for converting request bodies into Java objects.
* Security configuration isolated to a single file.
* Comments with explanations of why some features were implemented a certain way.


### JSR-303 Bean Validation API

This project uses the hibernate implementation of JSR-303. In the WelcomeController class, the addPerson method includes a @Valid annotation in front of the person parameter. This tells Spring that it should validate the new person object using the validation annotations on the Person class. 


### HttpMessageConverters

HttpMessageConverters is the magic that provides automatic conversion of Java objects to XML and JSON. In the rest-demo-servlet.xml, the 
section starting with `<mvc:annotation-driven>` automatically creates some standard HttpMessageConverters based on what jars are on the classpath. In particular, if the 
Jackson library is on the classpath, then a JSON converter will be created. We also add an XML marshaller based on XStream. We do this manually because by default Spring doesn't seem to do it quite right.


### Start.java

This is the class that runs an embedded Jetty server with the servlet. 

### WelcomeController.java

This is responsible for handling the various requests. Methods are annotated with `@RequestMapping` which indicates which url paths each method can handle, as well as what http request methods are supported. Each `@RequestMapping` method has logic to handle a particular operation on the people service. 
There are also two error handling methods (annotated with `@ExceptionHandler`) to deal with the situation where the input data is not valid and we need to show the user a description of the error.

How to check the Controller method
----------------------------------

The URL paths are hard to explore in a browser as you cannot easily control the type of request made and the request body. Firefox and Chrome both have plugins to assist with making requests to REST services. 

The client used to test this demo during development is rest-client, available from http://code.google.com/p/rest-client/. It is written in Java, and is cross platform. It has support for all HTTP request methods, as well as custom headers and setting the request body.



TODO
====

* Examples of testing Spring Controllers
