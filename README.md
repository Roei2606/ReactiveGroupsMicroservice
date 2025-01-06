# Reactive Groups Microservice

## Overview
This project implements a **Reactive Microservice** for managing user groups. The service allows users to:
- Create, update, and delete groups.
- Add or remove users from groups.
- Retrieve groups and users with pagination and sorting.

The microservice is written in **Kotlin** using **Spring Boot (WebFlux)** for its reactive capabilities and uses **MongoDB** as the database.

---

## Features
- **Reactive Programming**: Built with Spring WebFlux for non-blocking, high-performance services.
- **Pagination and Sorting**: Efficient data retrieval with support for pagination and deterministic sorting.
- **REST API**: Fully REST-compliant API endpoints.

---

## Requirements
### To run the project, ensure the following are installed:
1. **Java Development Kit (JDK)**: Version 17 or higher.
2. **Docker**: For running the MongoDB database.
3. **Gradle**: Version 7.6 or higher (if not bundled with the project).
4. **Kotlin**: Included in the project via dependencies.
5. **MongoDB**: Runs as a Docker container.

---

## Setup Instructions
### 1. Clone the repository
```bash
git clone <repository-url>
cd reactive-groups-microservice
```

### 2. Start MongoDB using Docker Compose
A `compose.yaml` file is provided for convenience. To start MongoDB:
```bash
docker-compose up -d
```

### 3. Build the project
Use Gradle to build the project:
```bash
./gradlew clean build
```

### 4. Run the application
Start the application:
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080` by default.

---

## API Endpoints
The service provides the following endpoints:
- **Group Management**:
  - `POST /groups` - Create a new group.
  - `GET /groups/{groupId}` - Get group details by ID.
  - `PUT /groups/{groupId}` - Update group details.
  - `DELETE /groups` - Delete all groups.
- **User Management**:
  - `PUT /groups/{groupId}/users` - Add a user to a group.
  - `GET /groups/{groupId}/users` - Get all users in a group (with pagination).
  - `DELETE /groups/{groupId}/users` - Remove all users from a group.
- **User-Group Associations**:
  - `GET /groups/{email}/groups` - Get all groups a user is associated with (with pagination).

---

## Testing
You can use tools like **Postman** or **Swagger** to test the endpoints. Swagger is available at:
```
http://localhost:8080/swagger-ui.html
```

---

## Notes
- The database (MongoDB) is initialized with the `mydatabase` name as defined in the `compose.yaml` file.
- Ensure the Docker container is running before starting the application.

---

## Built With
- **Kotlin**: Primary programming language.
- **Spring Boot (WebFlux)**: Framework for reactive programming.
- **MongoDB**: Reactive NoSQL database.
- **Docker**: For containerized MongoDB setup.

---

If you encounter any issues or need further assistance, feel free to reach out! 😊

---