# Prime Gift Store

### Stack
- Java 11+
- Gradle
- Spring (boot, data)
- Geode/Gemfire

### Profiles

Runnable in two spring profiles:
- geode.server
- geode.client

Please make sure to run the application in both profiles either in your IDE or Terminal.  

### Rest APIs
- [Product Services](postman/Order-APIs.postman_collection.json)
- [Order Services](postman/Product-APIs.postman_collection.json)

### UI 
- Access Prime Gift-Stroe Management UI at: `http://localhost:8080/store/products`

### Geode 

A standalone Geode instance can run in [docker](docker/docker-compose.yaml)
