package com.kernelx.mqttSubscriber.service;

import com.kernelx.mqttSubscriber.entity.Telemetry;
import com.kernelx.mqttSubscriber.repository.TelemetryRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryRepository repository;

    @Transactional
    public void saveTelemetry(Telemetry telemetry) {
        System.out.println("Saving: telemetry");
        repository.save(telemetry);
    }
}

