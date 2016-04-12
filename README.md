spring-mvc 
===

[![Build Status](https://travis-ci.org/mycoin/spring-mvc.svg?branch=master)](https://travis-ci.org/mycoin/spring-mvc)
This project will allow you to start off on the good foot with spring MVC. In this showcase you'll see the following features in action:

- Pure maven dependencies
- Embedded Jetty
- Mapping Requests
- Message Converters
- Rendering Layout Views
- Exception Handling


### Get start
---
Clone the repository:

```shell
git clone git@github.com:mycoin/spring-mvc.git
mvn -U clean install -Dmaven.test.skip=true
cd app && mvn jetty:run
```

or

Import the project as a maven project into your IDE, Run the class `org.ionnic.app.Main`
then open <http://127.0.0.1:8080/>


### Overview
---
This is an application to demo the implementation of a REST API.
> BTW: XML responses is not supported.

A better project structure is required:

- `app` the webapp contains the portal project.
- `biz` middleware, toolkit
- `common` config and application support, important config.

##### View

The `VelocityLayout` support. The velocity resource paths are:
- `/WEB-INF/views/` The primary template path
- `/WEB-INF/external/` velocity code snippets
- `~/output/` static external templates from SVN ? FTP or other services, At the same time, you can config it at `app:resources/config/global.properties`

Write your awesome template that assigned by the Controller in `/WEB-INF/views/` folder. 

Common modules such as header or footer locate in `/WEB-INF/external/`, then use `page.renderTpl(name, dataMap)` to import it.

Templates that frequently Modified will be managed by webservice, then sync then to `~/output/` folder





> see [net.breakidea.common.support.view.Viewport](https://github.com/mycoin/spring-mvc/blob/master/common/src/main/java/net/breakidea/common/support/view/Viewport.java) for detail

### Main.java

This is the class that runs an embedded Jetty server with the servlet. 

---

FreeBSD License