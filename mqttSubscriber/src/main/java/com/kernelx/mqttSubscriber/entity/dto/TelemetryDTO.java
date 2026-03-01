package com.kernelx.mqttSubscriber.entity.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.time.Instant;

@Data
public class TelemetryDTO {

    @NotBlank(message = "Device ID must not be empty")
    private String deviceId;

    @NotNull(message = "Value is required")
    private Double value;

    @NotNull(message = "Sensor health is required")
    @Min(value = 0, message = "Sensor health must be >= 0")
    @Max(value = 100, message = "Sensor health must be <= 100")
    private Integer sensorHealth;

    @NotNull(message = "Timestamp is missing")
    private Instant timestamp;

}