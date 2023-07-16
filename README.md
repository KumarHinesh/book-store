# book-store

# Book Cart Application

This project is a book cart application built using the Quarkus framework. It consists of two modules: one for creating JWT tokens and another for managing books and carts.

## Features

- JWT token generation and validation
- Book management (add, update, delete, get all)
- Cart management (create or get by user ID, add cart item, checkout)

## Technologies Used

- Quarkus: A cloud-native Java framework
- Java Persistence API (JPA) for database access
- SmallRye JWT for JWT token handling
- Docker for containerization

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven
- Docker (optional, for containerization)

### Build and Run

1. Clone the repository:

   ```bash
   git clone <https://github.com/KumarHinesh/book-store.git>


cd book-cart-app
mvn clean package


java -jar target/book-cart-app.jar


API Endpoints
Book Resource

POST /books - Add a new book
PUT /books/{id} - Update an existing book
DELETE /books/{id} - Delete a book
GET /books - Get all books
GET /books/{id} - Get a book by ID
Cart Resource

POST /carts/{userId} - Create or get a cart by user ID
POST /carts/{cartId}/items - Add a cart item to a cart
POST /carts/{cartId}/checkout - Checkout a cart and calculate the total
Configuration
The application requires the following configuration properties:

quarkus.datasource.jdbc-url - JDBC URL for the database
quarkus.datasource.username - Database username
quarkus.datasource.password - Database password
Docker Image
To build a Docker image of the application, use the following command:

