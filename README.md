清洁本地存储库
```
mvn dependency:purge-local-repository
```

确认未使用的依赖项
```
dependency:analyze
```

重设版本
```
mvn -N io.takari:maven:wrapper -Dmaven=3.1.0
```
