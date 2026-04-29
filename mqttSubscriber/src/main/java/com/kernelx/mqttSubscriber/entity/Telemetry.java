package com.kernelx.mqttSubscriber.entity;


import jakarta.persistence.*;
import java.time.Instant;

import lombok.*;

@Entity
@Table(name = "sensor_reading")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {

    @EmbeddedId
    private TelemetryId id;

    @Column(name = "measurement")
    private Double value;

    @Column(name = "battery_status")
    private Integer sensorHealth;
}
