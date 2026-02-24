package com.kernelx.mqttSubscriber.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryId implements Serializable {

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "timestamp")
    private Instant timestamp;
}
