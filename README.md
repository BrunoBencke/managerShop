# 🛒 Manager Shop API

Manager Shop API is a **RESTful API** built with **Spring Boot**, following best practices such as **Domain-Driven Design (DDD), SOLID principles, and Clean Architecture**.  
It provides **CRUD operations for product categories**, integrated with a **PostgreSQL database**, and includes **Swagger documentation**.

---

## 📌 Features

✅ RESTful API with CRUD operations  
✅ Uses **Spring Boot, Spring Data JPA, PostgreSQL**  
✅ Implements **DTO pattern** to separate API and domain logic  
✅ **Swagger UI** for API documentation  
✅ **Unit & Integration tests** using JUnit and Mockito  
✅ Follows **SOLID principles and Clean Architecture**  
✅ Logs managed with configurable logging levels

---

## 📌 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger (Springdoc OpenAPI)**
- **JUnit 5 & Mockito** (for testing)

---

## 📌 Installation Guide

### 🔹 **Prerequisites**
Ensure you have the following installed:
- **Java 17+**
- **Maven**
- **PostgreSQL**
- **Git**

### 🔹 **Clone the repository**
```bash
git clone https://github.com/your-username/manager-shop.git
cd manager-shop
```

### 🔹 **Configure the database**
Update src/main/resources/application.properties with your PostgreSQL credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/managershop
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 🔹 **Build and run the project**
- **mvn clean install**
- **mvn spring-boot:run**

### 🔹 **API Documentation**
Swagger UI:
👉 http://localhost:8080/swagger-ui.html

## 📌 Running Tests

### 🔹 **Run Unit Tests**
- **mvn test**

### 🔹 **Run Integration Tests**
- **mvn verify**