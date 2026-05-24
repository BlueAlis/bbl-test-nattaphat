BBL - Spring Boot REST API

Java 21 / Spring Boot 4.0.6 / PostgreSQL


Run

1. docker-compose up -d
2. ./mvnw spring-boot:run  (Windows: mvnw.cmd spring-boot:run)
3. http://localhost:8080


API

- GET    /users
- GET    /users/{id}
- POST   /users
- PUT    /users/{id}
- DELETE /users/{id}
- POST   /users/batch


Test

./mvnw test

SHOULD DO

- JWT Auth
- Swagger
- User Role
- More Validation
- Safe Delete
