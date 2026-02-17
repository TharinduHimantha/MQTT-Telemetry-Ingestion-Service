package com.kernelx.mqttSubscriber.entity.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TelemetryDTO {
    private String deviceId;
    private Double value;
    private Integer sensorHealth;
    private Instant timestamp;
}