# Greethy Project: Backend

---

## Current Status 🚧
> Due to the busy schedules of our team members, the Greethy project has been temporarily paused. We hope to resume work on this app in the future, aiming to fulfill our vision of building a healthier community.

## What’s Next 🔮
While Phase 2 of development will be delayed, we continue to maintain our roadmap and are committed to returning to this project when time permits. Stay tuned for future updates and development!

## Stay Connected 📡
Follow the progress of Greethy and keep an eye out for announcements about the reopening of the project. Thank you for your support!

### Features We’ve Planned

- **Health and Nutrition Tracking**: Monitor your diet, exercise, and financial well-being.
- **Community Interaction**: Connect with others on their health journey.
- **Resources**: Access reliable information and advice on healthy living.

We’ll be updating the status and progress of the project in the coming months, so keep following us for news!

## Thank You for Your Support 🙏

Greethy’s goal is to improve lives and foster a community of health-conscious individuals. We appreciate your patience and support during this period.

🚀

## Worklog:
You can find the worklogs for the Greethy project in the following documents:

- [2024 Project Worklog](https://docs.google.com/spreadsheets/d/1rBK_Y8xn-JQktNi-XbpwOGNDyIzXeSMfjy1KaiPRWK8/edit?usp=sharing)
- [2023 Project Worklog](https://docs.google.com/spreadsheets/d/184cta1VnCs9KNoSxxu4M1l1spFMGwVKs/edit?usp=sharing&ouid=100409196411014517948&rtpof=true&sd=true)

For inquiries, feel free to reach out via:
- Email: [chunhthanhde.dev@gmail.com](mailto:chunhthanhde.dev@gmail.com)
- Email: [greethy.project@gmail.com](mailto:greethy.project@gmail.com)
  
---

## 👋 About this Project

**Greethy** is a project that offers solutions for nutrition management, workout support, personal finance tracking, and a mini social network where people can join together to manage
and share their achievements.

**Greethy Server**, powered by `Spring Framework`, `Axon Framework`, `Apache Kafka` and `MongoDB`, provides efficient handling of large-scale data, project scalability, resilience, 
flexibility, security and seamless integration capabilities.

## 📋 Table of Contents

[Greethy Project: Backend](#greethy-project-backend)
* 👋 [About this Project](#-about-this-project)
* 📋 [Table of Contents](#-table-of-contents)
* 📝 [Prerequisites of Development](#-prerequisites-of-development)
* 🏛️ [Project Architecture](#-project-architecture)
  * 🏙️ [Architecture Implementation](#-architecture-implementation)
  * 🎡 [Microservice Component](#-microservice-component)
  * 📦 [Modular Component](#-modular-component)
  * 📁️ [File Structure](#-file-structure)
  * 👟 [Run the Application](#-run-the-application)
    * 🖥️ [Running on Local Machine](#-running-on-local-machine)
    * ⛴️ [Using Docker (Not finished yet)](#-using-docker-not-finished-yet)

## 📝 Prerequisites of Development

* [Git](https://git-scm.com/downloads)
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Docker Desktop](https://docs.docker.com/get-docker/) 

## 🏛️ Project Architecture

### 🏙️ Architecture Implementation

### 🎡 Microservice Component

*Greethy Server* consists of multiple services represented as modules in the project file, including:

* **Configuration Server** ([config-server](config-server/README.md)): is Spring's client/server approach for storing and serving distributed configurations across multiple modules 
and environments.

* **Service Discovery** ([services-discovery](services-discovery/README.md)): is a Microservice Component help to know where each instance of a service is located. It acts as a registry
in which the addresses of all instances are tracked.

* **API Gateway** ([api-gateway](api-gateway/README.md)): 

### 📦 Modular Component

* **Common** ([common](common/README.md)): 

### 📁 File Structure

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



## 👟 Run the Application

### 🖥️ Running on Local Machine

Go to [run-all-modules.sh](run-all-modules.sh) to run all services component in project. Remember run the service by order from top to bottom

### ⛴️ Using Docker (Not finished yet)

#### Overview

`Docker` is an open source platform for developing, shipping and running applications. It enables to separate applications from infrastructure so that can deliver software quickly. 

Reference: https://docs.docker.com/get-started/overview/

#### Install Docker

#### Run MongoDB Server

* Start MongoDB Instance: Use the following content for the [docker-compose.yaml](/greethy-docker/docker-compose.yaml) file, then run the service.

````yaml
#MONGODB FOR DATA RESOURCE
mongodb:
  image: mongo:latest
  container_name: greethy_mongodb
  hostname: mongodb
  ports:
    - "27017:27017"
  volumes:
    - ./db-data/mongodb:/data/db
  environment:
      - MONGO_INITDB_DATABASE=GreethyDB
      - MONGO_INITDB_ROOT_USERNAME=root
      - MONGO_INITDB_ROOT_PASSWORD=password
````

* Test MongoDB connection with `Intellj Database`: Go to View > Tool Windows > Database, click New > Data Source > MongoDB. Set configuration by following properties:

  * Host: localhost

  * Port: 27017

  * Authentication: Username Password

  * User: root

  * Password: password


### See API documentation at localhost:8085/swagger-ui.html
