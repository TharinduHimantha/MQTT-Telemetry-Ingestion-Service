package com.kernelx.mqttSubscriber.entity.dto;

import lombok.Data;

@Data
public class TelemetryDTO {
    private String deviceId;
    private Double temperature;
    private Double humidity;
}