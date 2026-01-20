# Proyecto Agenda
Sistema de agenda compuesto por **Frontend**, **Backend** y **Base de
Datos Oracle**, diseñado para ejecutarse en entorno de desarrollo local


------------------------------------------------------------------------
## Arquitectura del Proyecto
Estructura general del repositorio:

    proyecto-agenda/
    │
    ├── frontend/                 # Frontend Angular 19
    │   ├── Dockerfile
    │   └── ...
    │
    ├── pruebaAgenda/              # Backend Spring Boot 3 (Java 17)
    │   ├── Dockerfile
    │   └── ...
    │
    ├── oracle-db/                 # Base de datos Oracle XE
    │   ├── Dockerfile
    │   ├── script.sql             # Script de creación de objetos y datos
    │   └── ...
    │
    ├── docker-compose.yml         # Orquesta SOLO la base de datos
    └── README.md

------------------------------------------------------------------------

## Tecnologías Utilizadas

### Frontend
-   Angular 19
-   Node.js 24
-   TypeScript
-   HTML / CSS
-   Visual Studio Code

### Backend

-   Java 17
-   Spring Boot 3
-   Maven
-   JPA / Hibernate
-   REST API
-   Eclipse / STS

### Base de Datos

-   Oracle Database 21c XE
-   SQL
-   Docker

### Infraestructura

-   Docker
-   Docker Compose

------------------------------------------------------------------------

## Modelo de Ejecución del Proyecto

> Docker Compose se utiliza únicamente para levantar la base de
> datos Oracle.**

-   Base de datos → Docker Compose
-   Backend → Ejecutado desde IDE Java
-   Frontend → Ejecutado desde Visual Studio Code

------------------------------------------------------------------------

## Base de Datos Oracle (Docker)

### Credenciales de Conexión

  Parámetro   Valor
  ----------- -----------
  Host        localhost
  Puerto      1521
  SID         XE
  PDB         XEPDB1
  Usuario     AGENDA
  Password    agenda123

------------------------------------------------------------------------

## Levantar Base de Datos

``` bash
docker-compose up -d --build
```

------------------------------------------------------------------------

## Backend -- Spring Boot 3

```
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=AGENDA
spring.datasource.password=AG3nd5
```

------------------------------------------------------------------------

## Frontend -- Angular 19

``` bash
cd frontend
npm install
ng serve
```
