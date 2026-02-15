# MQTT-Telemetry-Ingestion-Service
Enterprise-grade MQTT telemetry ingestion microservice built with Spring Boot, Mosquitto, and TimescaleDB for scalable time-series data processing.

---

## 📌 Overview

This service subscribes to MQTT topics following the pattern:

    application_code/sensor_type/senor_id

Incoming telemetry messages are processed and stored in a TimescaleDB hypertable optimized for time-series workloads.

This project demonstrates a production-ready microservice architecture for IoT ingestion pipelines.

---

## 🏗 Architecture

           +------------------+
           |   MQTT Devices   |
           +------------------+
                    |
                    v
           +------------------+
           |   Mosquitto      |
           |   MQTT Broker    |
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
- Time-series optimized storage (hypertables)
- Containerized microservice architecture
- Environment-based configuration
- Ready for horizontal scaling


---

## ⚙️ Prerequisites

- Docker Desktop
- Maven
- Java 17

---

## 🛠 Running the Application

### 1. Build the application
```bash
mvn clean package
```

### 2. Start the full stack
```bash
docker-compose up --build
```

This will start:
  Mosquitto MQTT Broker (Port 1883)
  TimescaleDB (Port 5432)
  Spring Boot Subscriber Service

---

## 🔐 Configuration

Database configuration is handled using environment variables:
```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

These are defined inside docker-compose.yml

