
lotus
===

[![Build Status](https://travis-ci.org/mycoin/spring-mvc.svg?branch=master)](https://travis-ci.org/mycoin/spring-mvc) This project will allow you to start off on the good foot with spring MVC. In this showcase you'll see the following features in action:

- Pure maven dependencies
- Embedded Jetty
- Mapping Requests
- Message Converters
- Message Source
- Rendering Layout Views
- Exception Handling


### Get start
---
Import the mysql script `lotus.web/db.sql` , modify the db config in `applicationContext-db.xml`~

Clone the repository and:

```shell
mvn -U clean install -DdownloadSources=true -Dmaven.test.skip=true
cd lotus.web && mvn jetty:run
```

or

Import the project as a maven project into your IDE,  Run the class `net.breakidea.web.Main`
then open <http://127.0.0.1:8080/index.html>


### Overview
---
This is an application to demo the implementation of a REST API.
> BTW: XML responses is not supported.

A better project structure is required:

- `lotus.web` the webapp contains the portal project.
- `lotus.api` api module, 
- `lotus.public` front  page, based on `webpack` + `Vue`.
- `lotus.common` config and application support, important config.

##### View

The `Layout` support. The velocity resource paths are:
- `/WEB-INF/views/` The primary template path
- `/WEB-INF/common/` velocity code snippets
- `~/output/` static external templates from SVN ? FTP or other services, At the same time, you can config it at `app:global.properties`

Write your awesome template that assigned by the Controller in `/WEB-INF/views/` folder. 

Common modules such as header or footer locate in `/WEB-INF/common/`, then use `context.render(name, data)` to import it.

Templates that frequently modified will be managed by other service, then sync then to `~/output/` folder~

For context, the default attributes are:

- `application` the `WebApplicationContext` instance
- `request` the current `HttpServletRequest`
- `response` the current `HttpServletResponse`
- `page` the current context helper,  `net.breakidea.common.core.ContextHolder`
- `context` the context it's self
- `util` tool, static class for `net.breakidea.common.web.view.support.Utils`

> see [@ContextHolder](https://github.com/mycoin/lotus/blob/feature/spring/lotus.common/src/main/java/net/breakidea/common/web/view/support/ContextHolder.java) for detail

##### macro

Lots of macros , such as `block` ,  `extends` , `showContent` , `showInet` , `showMessage`  and so on. [click here](https://github.com/mycoin/lotus/blob/master/lotus.common/src/main/resources/modules/global.vm) for details.

##### message and url

Use `MessageSource` to manage texts, message and config. see file `applicationContext.xml`

- global message and important config `classpath:global.properties`
- application base url (with domain and `/` ) `system.server.base`  e.g http://www.baidu.com/
- CDN style resource `system.server.static`  e.g https://astyle.alicdn.com/1.0.0/ , see more [at here](https://github.com/mycoin/lotus/blob/master/lotus.common/src/main/resources/modules/global.vm#L126) 



### Main.java

This is the class that runs an embedded Jetty server with the servlet. 

---

FreeBSD License
