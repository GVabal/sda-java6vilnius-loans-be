# Loans Service

### Run
`./gradlew clean build && java -jar build/libs/*.jar`

### Endpoints
`POST /api/auth/register` - Register new user with USER_ROLE. 

Requires body: {"username":"string", "email": "string", "password": "string"}

`POST /api/auth/login` - Receive JWT token for user.

Requires body: {"email": "string", "password": "string"}

`POST /api/auth/refresh-token` - Refresh JWT token for user.

Requires body: {"refreshToken": "string"}
