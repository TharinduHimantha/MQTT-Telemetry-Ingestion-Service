# MQTT-Telemetry-Ingestion-Service
Enterprise-grade MQTT telemetry ingestion microservice built with Spring Boot, Mosquitto, and TimescaleDB for scalable time-series data processing.

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📌 Overview

This service subscribes to MQTT topics following the pattern:

    application_code/sensor_type/senor_id

Incoming telemetry messages are processed and stored in a TimescaleDB hypertable optimized for time-series workloads.

This project demonstrates a production-ready microservice architecture for IoT ingestion pipelines.

#### Important
  Please dedicate port 8091 for this microservice
  
---

## 🏗 Architecture

           +------------------+
           |   MQTT Devices   |
           +------------------+
                    |
                    v
           +------------------+
           |   MQTT Broker    |
           | (of your choice) |
           +------------------+
                    |
                    v
           +------------------+
           |  Spring Boot     |
           |  Subscriber      |
           +------------------+
                    |
                    v
           +------------------+
           |  TimescaleDB     |
           |  (PostgreSQL)    |
           +------------------+


---

## 🧰 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Eclipse Paho MQTT Client
- TimescaleDB
- Docker
- Docker Compose

---

## 🚀 Features

- MQTT topic wildcard subscription
- Real-time telemetry ingestion
- Batch insertion for optimized performance
- Time-series optimized storage (hypertables)
- Containerized microservice architecture
- Environment-based configuration
- Ready for horizontal scaling


---

## ⚙️ Prerequisites

- Docker Desktop
- Maven
- Java 21

---

## 🛠 Running the Application

### 1. Build the application
```bash
mvn clean package
```
or
```bash
mvn package -Dskip Tests
```

### 2. Build the Docker image
```bash
docker build -t mqtt-subscriber-service .
```

### 3. Run the Docker image as a container
```bash
docker run --name mqtt-subscriber-container -p 8091:8080 mqtt-subscriber-service
```

This will start:
  Spring Boot Subscriber Service on localhost 8091

#### Important
  The localhost port 8091 was selected for running the application
  TimescaleDB must be already running on Port 5432 for this to work

---

## 🔐 Configuration

Database configuration is handled using environment variables:
```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

These are defined inside docker-compose.yml

