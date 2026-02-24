package com.kernelx.mqttSubscriber.service;

import com.kernelx.mqttSubscriber.entity.Telemetry;
import com.kernelx.mqttSubscriber.repository.TelemetryRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryRepository repository;

    public void saveTelemetry(Telemetry telemetry) {
        repository.save(telemetry);
    }
}

