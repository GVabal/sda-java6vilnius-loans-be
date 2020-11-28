# Loans Service
Application that very (very) naively imitates the process of taking and paying back a loan. Customers register on the website, where they can apply for a loan, manage currently active loans. Employees can approve or reject loan applications. Admin can manage the employees.

This is back-end part for https://github.com/GVabal/sda-java6vilnius-loans-fe

#### Technologies used:

Java 11, Spring Boot 2.3.5, Spring Data JPA, Spring Security, JWT, MySQL, Flyway, Gradle

### Run this service locally:

Requires MySQL server. After downloading the project, configure data source in <a href="https://github.com/GVabal/sda-java6vilnius-loans-be/blob/master/src/main/resources/application.properties">src/main/resources/application.properties</a> file:
```
spring.datasource.username={username}
spring.datasource.password={password}
spring.datasource.url=jdbc:mysql://localhost:3306/{database}
```

:exclamation: NOTE: `jwt.secret` is revealed intentionally, in production this property should be externalized.

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
* [Authorization](#authorization)
  * POST /api/auth/register
  * POST /api/auth/login
  * POST /api/auth/refresh-token
* [Loan Applications](#loan-applications)
  * customer
    * GET /api/loan-applications/customer
    * POST /api/loan-applications/customer
    * POST /api/loan-applications/customer/:id
  * employee
    * GET /api/loan-applications/employee
    * POST /api/loan-applications/employee/approve/:id
    * POST /api/loan-applications/employee/reject/:id
* [Loans](#loans)
  * GET /api/loans
* [Employees](#employees)
  * GET /api/employees
  * POST /api/employees/register
  * DELETE /api/employees/:id
* [Payments](#payments)
  * POST /api/payments


### Authorization
**`POST /api/auth/register`** - Registers new Customer. 

Requires body: 
```
{
  "firstName":"string",
  "lastName":"string",
  "email": "string",
  "password": "string",
  "bankAccountNumber":"string"
}
```
Returns:
```
{
    "accessToken": "JTW token",
    "refreshToken": "JWT token",
    "user": {
        "email": "string",
        "role": ROLE[ROLE_CUSTOMER, ROLE_EMPLOYEE, ROLE_ADMIN]
    }
}
```

----
**`POST /api/auth/login`** - Receives JWT token for user. 

Requires body: 
```
{
  "email": "string",
  "password": "string"
}
```
Returns: `200OK`

----
**`POST /api/auth/refresh-token`** - Refreshes JWT token for user.

Requires body: 
```
{
  "refreshToken": "JWT token"
}
```
Returns:
```
{
    "accessToken": "JTW token",
    "refreshToken": "JWT token",
    "user": {
        "email": "string",
        "role": ROLE[ROLE_CUSTOMER, ROLE_EMPLOYEE, ROLE_ADMIN]
    }
}
```

----
### Loan Applications
**`GET /api/loan-applications/customer`** - Gets all loan applications for customer.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns:
```
[
  {
    "id": number,
    "amount": number,
    "termMonths": number,
    "interestRatePerYear": number,
    "loanReason": "string",
    "status": ApplicationStatus[PENDING, APPROVED, REJECTED, TAKEN, COMPLETED]
  },
  ...
]
```

----
**`POST /api/loan-applications/customer`** - Creates new Loan Application by customer.

Requires header: `key: Authorization, value: Bearer JWT-token`

Requires body:
```
{
  "amount": number,
  "loanReason": "string",
  "termMonths": number
}
```

Returns:
```
{
  "id": number,
  "amount": number,
  "termMonths": number,
  "interestRatePerYear": number,
  "loanReason": "string",
  "status": ApplicationStatus[PENDING, APPROVED, REJECTED, TAKEN, COMPLETED]
}
```

----
**`POST /api/loan-applications/customer/:loan-application-id`** - Creates Loan by customer from Loan Application with APPROVED Application Status.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns: `200OK`

----
**`GET /api/loan-applications/employee`** - Gets all pending loans.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns:
```
[
  {
    "id": number,
    "amount": number,
    "termMonths": number,
    "interestRatePerYear": number,
    "loanReason": "string",
    "customer": {
      "id": number,
      "firstName": "string",
      "lastName": "string",
      "email": "string",
      "bankAccountNumber": "string"
    }
  },
  ...
]
```

----
**`POST /api/loan-applications/employee/approve/:id`** - Changes status of Loan Application to APPROVED.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns: `200OK`

----
**`POST /api/loan-applications/employee/reject/:id`** - Changes status of Loan Application to REJECTED.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns: `200OK`

----
### Loans
**`GET /api/loans`** - Gets all active Loans for customer.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns:
```
[
  {
    "id": number,
    "amountToRepay": number,
    "amountPayed": number,
    "status": Status[ACTIVE, TERMINATED],
    "customer": {
      "id": number,
      "firstName": "string",
      "lastName": "string",
      "email": "string",
      "bankAccountNumber": "string"
    }
  ...
]
```

----
### Employees
**`GET /api/employees`** - Gets all employees.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns:
```
[
  {
    "id": number,
    "pid": "string",
    "email": "string",
    "status": Status[ACTIVE, TERMINATED]
  },
  ...
]
```

----
**`POST /api/employees/register`** - Add new employee.

Requires header: `key: Authorization, value: Bearer JWT-token`

Requires body:

```
{
  "email": "string",
  "password": "string"
}
```

Returns:
```
{
  "id": number,
  "pid": "string",
  "email": "string",
  "status": Status[ACTIVE, TERMINATED]
}
```

----
**`DELETE /api/employees/:id`** - Disables/Terminates employee.

Requires header: `key: Authorization, value: Bearer JWT-token`

Returns: `200OK`

----
### Payments
**`POST /api/payments`** - Pays back the loan.

Requires header: `key: Authorization, value: Bearer JWT-token`

Requires body:
```
{
  "loanId": number,
  "amount": number
}
```

Returns: `200OK`
