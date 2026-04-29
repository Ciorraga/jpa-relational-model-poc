# JPA Relational Model POC

Proof of concept of a Spring Boot 3 application that exposes a relational domain model through a simple REST API backed by PostgreSQL.

The goal of this repository is to provide a small but realistic example of:

- mapping relational structures with JPA
- exposing read endpoints over related aggregates
- documenting the API with OpenAPI and Swagger UI
- keeping the codebase aligned with a modern Spring Boot 3 / Java 21 stack

## Tech Stack

- Java 21
- Spring Boot 3.5.13
- Spring Data JPA
- PostgreSQL
- MapStruct
- OpenAPI / Swagger UI
- Maven
- Docker Compose

## Project Scope

This repository is intentionally small. It focuses on read-only exploration of a relational model composed of sales, stores, users, products and status history.

It is not intended to be a production-ready application. Instead, it is a compact reference project that can be used to:

- understand entity relationships in a Spring Boot application
- explore DTO-based API exposure instead of returning JPA entities directly
- use MapStruct for mapping between entities and API contracts
- spin up a local environment quickly for demos, experiments or technical articles

## Running Locally

### 1. Start PostgreSQL

The repository includes a Docker Compose setup under `src/main/resources/docker_postgres_with_data`.
It starts PostgreSQL and initializes the schema and seed data from the SQL scripts in the `sql` folder.

```bash
cd src/main/resources/docker_postgres_with_data
docker compose up -d
```

PostgreSQL will be exposed at `localhost:5438`.

To verify that the container is running:

```bash
docker compose ps
```

Container and volume names:

- container: `jpa-relational-model-poc-postgres`
- volume: `jpa-relational-model-poc-postgres-data`

To recreate the database from scratch:

```bash
cd src/main/resources/docker_postgres_with_data
docker compose down -v
docker compose up -d
```

If you change the SQL seed scripts and want to see the new dataset, you need to recreate the volume with the same command.

### 2. Start the Application

From the project root:

```bash
mvn spring-boot:run
```

The API will be available at:

- `http://localhost:8080`

## Exploring the API

### Swagger UI

The easiest way to explore and test the API is through Swagger UI:

- `http://localhost:8080/swagger-ui.html`

From Swagger UI you can:

- inspect the available endpoints
- review request and response schemas
- execute requests directly from the browser

### OpenAPI Specification

The generated OpenAPI document is available at:

- `http://localhost:8080/v3/api-docs`

### Main Endpoints

This POC currently exposes these read endpoints:

- `GET /sales`
- `GET /stores`
- `GET /order-statuses`

## Implementation Notes

- The API returns `*DTO` contracts instead of exposing JPA entities directly.
- Entity-to-DTO mapping is handled with MapStruct.
- Service tests are unit tests with mocked collaborators rather than database-backed integration tests.
- The local PostgreSQL setup uses a Docker-managed volume to avoid host filesystem permission issues.

## Repository Purpose

This repository is useful as a lightweight companion for technical content around:

- JPA relationship modelling
- Spring Boot 3 modernization
- DTO mapping strategies
- API documentation with Swagger / OpenAPI
- local development with PostgreSQL and Docker Compose
