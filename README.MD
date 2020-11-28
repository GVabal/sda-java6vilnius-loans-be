# Loans Service

This is back-end part for https://github.com/GVabal/sda-java6vilnius-loans-fe

#### Technologies used:

Java 11, Spring Boot 2.3.5, Spring Data JPA, Spring Security, JWT, MySQL, Flyway, Gradle

### Run this application locally:

After downloading the project, run `./gradlew clean build bootRun` command. The application will run on http://localhost:8080.

### Endpoints
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
