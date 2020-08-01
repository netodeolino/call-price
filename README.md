# FaleMais

## Backend
- Java 11, Spring Boot, H2 Database, Swagger, Lombok, JUnit, Mockito
- Endpoint: http://localhost:8081
- Swagger: http://localhost:8081/swagger-ui.html

## Frontend
- Vue (CLI), Boostrap, Axios
- Endpoint: http://localhost:8080

## Passos para executar com o Docker

Na pasta do projeto executar o seguinte comando:
- docker-compose up --build

Observação: é necessário ter o docker e o docker-compose instalado na máquina!

## Passos para executar na máquina

### Frontend
Na pasta falemais-front executar:
- npm install
- npm run serve

### Backend
Na pasta falemais-api:
- ./mvnw spring-boot:run


Observação: alguns desses comandos podem mudar em ambientes Windows!