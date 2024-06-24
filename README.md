# Greethy Project: Backend

## 👋 About this Project

**Greethy** is a project that offers solutions for nutrition management, workout support, personal finance tracking, and a mini social network where people can join together to manage
and share their achievements.

**Greethy Server**, powered by `Spring Framework`, `Apache Kafka` and `MongoDB`, provides efficient handling of large-scale data, project scalability, resilience, 
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

* **API Gateway** ([api-gateway](api-gateway/README.md)): 

### 📦 Modular Component

### 📁 File Structure

## Technologies Stack



## 👟 Run the Application

### 🖥️ Running on Local Machine

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

### Getting Started
To install this project, run the following commands:
1. **Clone the repository**:
```
git clone https://github.com/ChunhThanhDe/greethy-backend.git
```
2. **Navigate to the project directory**:
```
cd greethy-backend
```
3. **Build the project**:
```
mvn clean install
```
4. **Run Docker-Compose**:
```
cd greethy-backend/greethy-docker
docker-compose up -d
```
5. **Run the Application**:
Open your Git bash and run this command:
```
./run-project.sh
```

