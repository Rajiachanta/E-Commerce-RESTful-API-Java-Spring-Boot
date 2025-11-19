# E-Commerce REST API - Spring Boot Backend

This is a Spring Boot backend application for e-commerce operations, featuring JWT authentication and MySQL database integration.

## Project Overview

Built a secure e-commerce backend API using Spring Boot 3 with JWT-based authentication. The system handles user registration, login, and protected endpoints with proper security measures.

## Technologies Used

- Java 21
- Spring Boot 3.5.7
- Spring Security 6
- JWT for authentication
- MySQL database
- Maven for dependency management
- Spring Data JPA

## Key Features

- User registration and login system
- JWT token-based authentication
- Password encryption using BCrypt
- Protected API endpoints
- MySQL database integration
- RESTful API design

## API Endpoints

### Public Routes
- POST /api/users/register - Create new user account
- POST /api/users/login - User authentication, returns JWT token

## Setup Instructions

1. Clone this repository
2. Update application.properties with your MySQL database configuration
3. Run the application using: mvn spring-boot:run
4. The API will be available at http://localhost:8080

## Authentication

After successful login, include the JWT token in subsequent requests using the Authorization header:
Authorization: Bearer <jwt_token>

## Project Structure

Standard Spring Boot structure with separate packages for controllers, services, entities, and configuration classes. JWT utilities and security configuration are handled in the config package.
