# Greethy Project: Backend

## About this Project

**Greethy** is a project that offers solutions for nutrition management, workout support, personal finance tracking, and a mini social network where people can join together to manage and share their achievements.

**Greethy Server**, built with `Spring Boot`, provides efficient handling of large-scale data, project scalability, resilience, flexibility, security and seamless integration capabilities.

## Table of Contents

* [Greethy Project: Backend](#greethy-project-backend)
  * [About this Project](#about-this-project)
  * [Table of Contents](#table-of-contents)
  * [Project Architecture](#project-architecture)
    * [Architecture Implementation](#architecture-implementation)
    * [Microservice Component](#microservice-component)
    * [Modular Component](#modular-component)
    * [Project Structure](#project-structure)
  * [Run the Application](#run-the-application)
    * [Running on Local Machine](#running-on-local-machine)
    * [Running using Docker (Not finished yet)](#running-using-docker-not-finished-yet)

## Project Architecture

### Architecture Implementation

### Microservice Component

*Greethy Server* consists of multiple services represented as modules in the project file, including:

* **Configuration Server** ([config-server](config-server/README.md)): is Spring's client/server approach for storing and serving distributed configurations across multiple modules and environments.

* **Service Discovery** ([services-discovery](services-discovery/README.md)): is a Microservice Component help to know where each instance of a service is located. It acts as a registry in which the addresses of all instances are tracked.

* **API Gateway** ([api-gateway](api-gateway/README.md)): 

### Modular Component

* **Common** ([common](common/README.md)): 

* **Infrastructure** ([infrastructure](infrastructure/README.md)): 

### Project Structure

```
greethy-backend
  ├─┬ api-gateway           → gateway module with Spring Cloud Gateway
  │ ├─ src
  │ ├─ Dockerfile
  │ └─ pom.xml
  ├─┬ common                → common module for shared classes
  │ ├─┬ annotation             → annotation sub-module for customized annotation
  │ │ ├─ src
  │ │ └─ pom.xml
  │ ├─┬ mapper                 → mapper sub-module for mapper configuration
  │ │ ├─ src
  │ │ └─ pom.xml
  │ └─ pom.xml
  │
  │
  ├─┬ services-discovery    → services discovery module with Eureka Server
  │ ├─ src
  │ ├─ Dockerfile
  │ └─ pom.xml
  └─ pom.xml       → Maven parent pom managing both modules
```

## Technologies Stack

## Run the Application

### Running on Local Machine

Go to [run-all-modules.sh](run-all-modules.sh) to run all services component in project. Remember run the service by order from top to bottom

### Running using Docker (Not finished yet)



