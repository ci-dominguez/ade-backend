# 🏡 Ade - Spring-Java REST API

The backend for Ade is a Spring Boot REST API. It handles business logic, data storage, and communication with the PostgreSQL database hosted on Neon. The backend is deployed on Koyeb. The frontend can be found [here](https://github.com/cdom27/ade-react-typescript-frontend).

## ✨ Features

- **RESTful Endpoints**: Provides endpoints for managing homes, contact messages, and visit requests.
- **Database Integration**: Interfaces with a PostgreSQL database to store and retrieve data.
- **DTOs for Frontend Communication**: Supplies structured data for the React frontend.
- **Security & CORS Configurations**: Ensures secure and cross-origin communication.
- **Entity Models**:
  - `ContactMessage`: Stores user inquiries.
  - `Home`: Represents homes available for sale.
  - `HomeDetails`: Provides additional details about homes.
  - `Visit`: Manages visit scheduling information.

## Made with some really cool tools 👇

- ☕ [Spring Boot](https://spring.io/projects/spring-boot)
- 🐘 [PostgreSQL](https://www.postgresql.org/)
- 🔒 [Spring Security](https://spring.io/projects/spring-security)
- 📦 [Hibernate ORM](https://hibernate.org/)
