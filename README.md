
lotus
===
[![Build Status](https://travis-ci.org/mycoin/lotus.svg?branch=master)](https://travis-ci.org/mycoin/lotus)
This project will allow you to start off on the good foot with spring MVC. In this showcase you'll see the following features in action:

- Pure maven dependencies `only spring, commons`
- Embedded Jetty
- Mapping Requests
- Message Converters
- Message Source `i18n support`
- Rendering Layout Views
- Exception Handling
- Multi environment packaging `profiles`


### Get start
---
Clone the repository and modify the db config in `classpath:conf/dev.properties `  then import the mysql script `lotus.web/db.sql `!

run:

```shell
mvn -U clean install
cd lotus.web && mvn jetty:run
```

or

Import the project as a maven project into your IDE,  Run the class `net.breakidea.web.Main`
then open <http://127.0.0.1:8080/index.html>

the online demo https://api.breakidea.net/index

### Overview
---
This is an application to demo the implementation of a REST API.
> BTW: XML responses is not supported.

A better project structure is required:

- `lotus.web` the webapp contains the portal project.
- `lotus.api` api module, 
- `lotus.public` front  page, based on `webpack` + `Vue`.
- `lotus.common` config and application support, important config.

##### packaging

Use maven property named `environment, conf`  to  support `dev` , `test`  and `production` environments, e.g:

```
mvn install -P production
```

- default actived environment is `dev`
- use `environment` as global runtime variable
- `conf` the location of `context:property-placeholder` .properties file. [see more](https://github.com/mycoin/lotus/blob/master/pom.xml)
- The messageSource (i18n) also includes property-placeholders

#####  View

The `Layout` support. The velocity resource paths are:
- `/WEB-INF/views/` The primary template path
- `/WEB-INF/common/` velocity code snippets
- `{server.output}extends/` provides static external templates, Use `oss:filename.html` to include it. 

Write your awesome template that assigned by the Controller in `/WEB-INF/views/` folder. 

Common modules such as header or footer locate in `/WEB-INF/common/`, then use `context.render(name, data)` to render it.

Templates that frequently modified will be managed by other service, then sync then to `{server.output}extends/` folder~

For rendering context, the default attributes are:

- `application` the `WebApplicationContext` instance
- `request` the current `HttpServletRequest`
- `response` the current `HttpServletResponse`
- `page` the current context helper,  `net.breakidea.common.core.ContextHolder`
- `context` the context it's self
- `util` tool, static class for `net.breakidea.common.web.view.support.Utils`

> see [@ContextHolder](https://github.com/mycoin/lotus/blob/feature/spring/lotus.common/src/main/java/net/breakidea/common/web/view/support/ContextHolder.java) for detail

##### macro

Lots of macros , such as `block` ,  `extends` , `showContent` , `showInet` , `showMessage`  and so on. [click here](https://github.com/mycoin/lotus/blob/master/lotus.common/src/main/resources/modules/global.vm) for details.

##### configure 🌿

Use maven profile to package diff environments. Between environments the only differences are property `conf`  &  `environment` . 

Load the environment' property by `ContextUtils.getEnv(key)`  that cached from file `classpath:global.properties` startWith `system`, e.g `url.base `, `domain`

- global important config `classpath:global.properties` and messages in `/WEB-INF/conf/`.
- application base url (with domain and `/` ) `app.url.base`  e.g http://www.baidu.com/
- load your `.properties` by `net.breakidea.common.core.PropertiesLoader@loadProperties`
- CDN style resource `app.url.assert`  e.g https://astyle.alicdn.com/1.0.0/ , see more [at here](https://github.com/mycoin/lotus/blob/master/lotus.web/src/main/resources/global.properties) 
- Project output path is `system.output`  external templates can be put here.
- for more, please read https://blog.breakidea.net/archives/279



### Main.java

This is the class that runs an embedded Jetty server with the servlet. 

---

FreeBSD License
