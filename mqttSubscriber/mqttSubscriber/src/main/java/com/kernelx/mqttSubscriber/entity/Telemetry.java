package com.kernelx.mqttSubscriber.entity;


import jakarta.persistence.*;
import java.time.Instant;

import lombok.*;

@Entity
@Table(name = "telemetry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {

    @EmbeddedId
    private TelemetryId id;

    @Column(name = "value")
    private Double value;

    @Column(name = "sensor_health")
    private Integer sensorHealth;
}
