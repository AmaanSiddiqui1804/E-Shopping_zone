# E-Shopping_Zone
# 🛒 E-Shopping Microservice Application

A scalable, modular, and production-ready **E-Commerce application** built using **Spring Boot microservices architecture**, with integrated features such as **JWT authentication**, **email notifications**, **Feign Clients**, and **service discovery using Eureka**.

---

## 📦 Microservices

| Microservice        | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| **API Gateway**     | Entry point of the system. Routes requests to services, and handles JWT auth. |
| **Eureka Server**   | Service Discovery Server for all microservices.                            |
| **User Service**    | Manages user data and address.                                              |
| **Product Service** | Handles product catalog and product metadata.                              |
| **Cart Service**    | Manages user shopping cart and cart items.                                 |
| **Order Service**   | Places and tracks orders, includes embedded cart snapshot.                 |
| **Notification Service** | Sends email confirmations and user credentials during onboarding.     |

---

## 🎯 Features

- ✅ **User registration & login** with email-based password delivery
- ✅ **JWT-based authentication** via API Gateway
- ✅ **Microservices communication** using Feign Clients
- ✅ **Service Discovery** with Eureka
- ✅ **Cart and Order management**
- ✅ **Centralized Logging** via AOP
- ✅ **Global Exception Handling**
- ✅ **Entity Validation** with annotations
- ✅ **Clean DTO-to-Entity mapping** using ModelMapper
- ✅ **Scalable modular architecture**

---

## 📚 Microservices Breakdown

### 📌 User Service
- Handles user registration, login, profile and address management.
- Password sent via email using Notification Service.
- Contains role-based authorization.

### 📌 Product Service
- Adds, updates, deletes, and retrieves products.
- Supports product types, categories, and discount handling.

### 📌 Cart Service
- Allows users to add/remove items, update quantity.
- Automatically calculates and updates total price.
- Uses Feign Clients to validate product and user existence.

### 📌 Order Service
- Places and manages orders.
- Stores snapshot of cart at order time for history.
- Includes `Status` enum: PLACED, SHIPPED, DELIVERED, CANCELLED.

### 📌 Notification Service
- Sends emails for registration, password sharing.
- Stateless and simple — no database.

---

## 🔐 Security

- Authentication is managed by **API Gateway**.
- Each service is protected via JWT Token validation filter.
- Users are authenticated based on roles like `USER`, `ADMIN`.

---

## 🧪 Testing

- Unit testing for services and repositories.
- Integration testing using Postman / Swagger.
- AOP logging for all critical methods and exceptions.

---

## 📊 Diagrams

- ✅ Entity-Relationship Diagrams (ERD)
- ✅ Component Interaction Diagram
- ✅ Sequence Diagrams (WIP)
- ✅ Deployment Diagram (WIP)

> Check the `/diagrams` folder for full visuals in PNG and PlantUML format.

---

## 🚀 Run Locally

### Prerequisites
- Java 21
- Maven
- MySQL
- Postman (for API testing)
