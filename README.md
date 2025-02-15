Below is an example of a README file that you can include in your repository. You can adjust or expand the content as needed:

---

```markdown
# Hotel Reservations API

This project is a RESTful API designed to manage hotel reservations. It provides endpoints for managing hotels, clients, and reservations, allowing you to perform CRUD operations on these entities. The API is built using Spring Boot and follows a layered architecture to keep the code clean and maintainable.

## Features

- **Hotel Management:** Create, read, update, and delete hotels.
- **Client Management:** Manage client information with full CRUD operations.
- **Reservation Management:** Create and manage reservations linking hotels with clients along with check-in and check-out dates.
- **API Documentation:** The API is documented using an OpenAPI specification, which can be imported into Postman for interactive documentation.

## Technologies Used

- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **PostgreSQL**
- **Springdoc OpenAPI (v2.8.3)**
- **Maven**

## Endpoints Overview

### Hotels

- **GET** `/api/hotels`  
  Retrieves a list of all hotels.

- **POST** `/api/hotels`  
  Creates a new hotel.  
  **Example Request Body:**
  ```json
  {
    "nome": "Hotel Paraíso",
    "localizacao": "Praia Central",
    "capacidade": 200
  }
  ```

- **GET** `/api/hotels/{id}`  
  Retrieves a hotel by its ID.

- **PUT** `/api/hotels/{id}`  
  Updates an existing hotel.  
  **Example Request Body:**
  ```json
  {
    "nome": "Hotel Renovado",
    "localizacao": "Centro",
    "capacidade": 150
  }
  ```

- **DELETE** `/api/hotels/{id}`  
  Deletes a hotel by its ID.

### Clients

- **GET** `/api/clientes`  
  Retrieves a list of all clients.

- **POST** `/api/clientes`  
  Creates a new client.  
  **Example Request Body:**
  ```json
  {
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "telefone": "123456789"
  }
  ```

- **GET** `/api/clientes/{id}`  
  Retrieves a client by its ID.

- **PUT** `/api/clientes/{id}`  
  Updates an existing client.

- **DELETE** `/api/clientes/{id}`  
  Deletes a client by its ID.

### Reservations

- **GET** `/api/reservas`  
  Retrieves a list of all reservations.

- **POST** `/api/reservas`  
  Creates a new reservation linking a hotel and a client with check-in and check-out dates.  
  **Example Request Body (using ReservaDTO):**
  ```json
  {
    "hotelId": 1,
    "clienteId": 1,
    "dataCheckin": "2025-03-01",
    "dataCheckout": "2025-03-05"
  }
  ```

- **GET** `/api/reservas/{id}`  
  Retrieves a reservation by its ID.

- **DELETE** `/api/reservas/{id}`  
  Deletes a reservation by its ID.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- PostgreSQL (Ensure it is installed and running)

### Installation

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   cd hotelreservations
   ```

2. **Build the Project:**
   ```bash
   mvn clean install
   ```

3. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```
   The API will be accessible at `http://localhost:8080`.

4. **API Documentation:**
   - **Postman:** Import the provided OpenAPI specification (ensure it uses an OpenAPI 3.0.x version) into Postman for interactive documentation.
   - **Swagger UI (Optional):** If configured, access Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

## Running Tests

Run the test suite using:
```bash
mvn test
```

## Author

Humberto Rosabal

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
```
