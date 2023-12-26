# Rock, paper, scissors test backend

## Backend architecture

The backend is designed using hexagonal architecture (ports and adapters).

There are three main packages:

- **adapters**: Contains the adapters (http, database, messaging, etc). Within this package, the adapters are divided by
  feature. Within each feature, there is a division between inbound and outbound adapters.
- **config**: Contains the application configuration (Spring Java config)
- **core**: Contains the application core. There is a package per each feature (aggregate) in the project. Within each
  feature package, there is an application package and a domain package.
  
The model has been designed using DDD tactical patterns:

- Aggregate
- Entity
- Value Object
- Repository
- Domain Events
  
## Requirements

For building and running the application you need:

- JDK 17
- Maven 3 (or use maven wrapper mvnw)

## Build the application

```
mvn clean package
```

## Running the application locally

There are several ways of running the application:

```
mvn spring-boot:run
```

If you build the application:

```
java -jar target/rpsgame.jar
```

## Running tests

```
mvn test
```

## API documentation

Build the jar and execute the project. The API documentation will be exposed in http://localhost:8080/doc/index.html