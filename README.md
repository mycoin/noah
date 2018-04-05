
lotus
===
[![Build Status](https://travis-ci.org/mycoin/lotus.svg?branch=master)](https://travis-ci.org/mycoin/lotus)
This project will allow you to start off on the good foot with spring MVC. In this showcase you'll see the following features in action:

- Pure maven dependencies
- Embedded Jetty
- Mapping Requests
- Message Converters
- Message Source `i18n support`
- Rendering Layout Views
- Exception Handling
- Multi environment packaging `profiles`


### Get start
---
Clone the repository and modify the db config in `classpath:conf/global.properties `  then import the mysql script `lotus.deploy/src/main/resources/*.sql `

run:

```shell
mvn -U clean install
cd lotus.bundle && mvn jetty:run
```

then open http://127.0.0.1:8080/

### Overview
---
This is an application to demo the implementation of a REST API.
> BTW: XML responses is not supported.

A better project structure is required:

- `lotus.shared` shared apis, models and service interfaces
- `lotus.dal` the data access layer, using [mybatis ](https://github.com/mybatis/)
- `lotus.biz` service implements
- `lotus.web` the front-end controllers
- `lotus.bundle` the bundle including templates , htdocs and `.xml`
- `lotus.deploy` make packages

##### packaging

Use maven property named `environment, conf`  to  support `dev` , `test`  and `production` environments, e.g:

```
mvn install -P production
```

- default actived environment is `dev`
- use `environment` as global runtime variable
- `conf` the location of `context:property-placeholder` .properties file. [see more](https://github.com/mycoin/lotus/blob/master/pom.xml)

---

FreeBSD License
