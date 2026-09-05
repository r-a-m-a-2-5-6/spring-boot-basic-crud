

# spring-boot-basic-crud

## How it works (WhiteBoard)

[Excalidraw WhiteBoard](https://excalidraw.com/#json=YwFbs7eW0kCz4o4r5uvCV,A0ED0VuiHO3llLsVbrvT9Q)

## Project Overview

This project is a basic **Spring Boot CRUD application** for managing student data.

The application follows a layered architecture using:

* **Controller**
* **Service**
* **Repository**
* **Entity**

The `Student` entity represents the student data stored in the SQL database.

## CRUD Operations

The application implements the following operations:

* **Create Student** – Add a new student.
* **Get Student** – Retrieve a student by ID.
* **Get All Students** – Retrieve all students.
* **Edit Student** – Update an existing student's data.
* **Delete Student** – Delete a student.
* **Soft Delete Student** – Mark a student as deleted without physically removing the record from the database.

## Architecture

The application follows the flow:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
SQL Database
```

### Controller

The `StudentController` handles HTTP requests and exposes the REST API endpoints.

### Service

The `StudentService` contains the business logic for student-related operations.

### Repository

The `StudentRepository` is responsible for interacting with the database using Spring Data JPA.

### Entity

The `Student` class represents the student entity and is mapped to the database using JPA annotations.

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* SQL
* Maven
* REST API
* JPA Annotations
* Spring Annotation-based Configuration

## Key Concepts Practiced

This project was built to understand the fundamentals of:

* Spring Boot project structure
* REST APIs
* Layered architecture
* Controller-Service-Repository pattern
* Dependency Injection
* Spring Data JPA
* Entity mapping
* SQL database integration
* Basic CRUD operations
* Soft delete
* Annotation-based configuration
