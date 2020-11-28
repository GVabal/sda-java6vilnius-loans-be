# Loans Service

This is back-end part for https://github.com/GVabal/sda-java6vilnius-loans-fe

#### Technologies used:

Java 11, Spring Boot 2.3.5, Spring Data JPA, Spring Security, JWT, MySQL, Flyway, Gradle

### Run this application locally:

Requires MySQL server. After downloading the project, configure data source in <a href="https://github.com/GVabal/sda-java6vilnius-loans-be/blob/master/src/main/resources/application.properties">src/main/resources/application.properties</a> file:
```
spring.datasource.username={username}
spring.datasource.password={password}
spring.datasource.url=jdbc:mysql://localhost:3306/{database}
```

:exclamation: NOTE: `jwt.secret` is revealed intentionally, in production this should not be revealed.

Then run `./gradlew clean build bootRun` command. The application will run on http://localhost:8080.

There are several preloaded users and data for demo purposes:

```
Login               | Password
-------------------------------
customer@email.com  | password
employee@email.com  | password
admin@email.com     | password
```

## Endpoints

#### Authorization
`POST /api/auth/register` - Register new Customer. Requires body: 
```
{
  "firstName":"string",
  "lastName":"string",
  "email": "string",
  "password": "string",
  "bankAccountNumber":"string"
}
```

`POST /api/auth/login` - Receive JWT token for user. Requires body: 
```
{
  "email": "string",
  "password": "string"
}
```

`POST /api/auth/refresh-token` - Refresh JWT token for user. Requires body: 
```
{
  "refreshToken": "string"
}
```
#### Loan Applications
#### Loans
#### Employees
#### Payments
