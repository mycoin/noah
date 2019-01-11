[![Build Status](https://travis-ci.org/mycoin/noah.svg?branch=master)](https://travis-ci.org/mycoin/noah)
This project will allow you to start off on the good foot with spring boot. In this showcase you'll see the following features in action:

- Pure maven dependencies
- Embedded Tomcat (Spring-boot)
- Using `redis` as Session Repository
- Message Converters
- Message Source `i18n support`
- Rendering Layout Views
- Multi environment packaging `profiles`

### Get start
---
Clone the repository and modify the db config in `application.properties` then import the mysql script `/support/db.sql `

- startup mysql 
- install redis and start it default port: 6379

run:

```shell
./mvnw -U clean install &&
./mvnw -U spring-boot:run --projects=noah-start --activate-profiles=dev
```

then open http://127.0.0.1:8080/

### Overview
---
This is an application to demo the implementation of a REST API.
> BTW: XML responses is not supported.

A better project structure is required:

- `noah-common` shared apis, models and service interfaces
- `noah-dao` the data access layer, using [mybatis ](https://github.com/mybatis/)
- `noah-service` service implements
- `noah-start` the front-end controllers, config files and velocity templates

##### packaging

Use maven property named `environment, conf`  to  support `dev` , `test`  and `production` environments, e.g:

```
mvn install -P production
```

- default actived environment is `dev`
- use `environment` as global runtime variable
- `conf` the location of `context:property-placeholder` .properties file. [see more](https://github.com/mycoin/noah/blob/master/pom.xml)

---

FreeBSD License
