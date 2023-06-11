# Spring Boot Login and Signup API with Security/Web Tokens

This project provides a backend implementation for a login and signup REST API with Security/Web Tokens using Spring Boot. It utilizes an H2 database for data storage.

## Prerequisites

- Java Development Kit (JDK) 17 used for developing this project
- Maven

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/SanskarxRawat/Signp-Login-SpringBoot.git
   ```

2. Navigate to the project directory:

   ```bash
   cd your-project
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

## Endpoints

- `POST /signup`: Register a new user. Requires a request body containing user details (username, email, password).
- `POST /login`: Authenticate a user and generate a JWT token. Requires a request body containing user credentials (email, password).

## Technologies Used

- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- H2 Database

## Configuration

- Database Configuration: The application is configured to use an H2 in-memory database. You can modify the database settings in the `application.properties` file.