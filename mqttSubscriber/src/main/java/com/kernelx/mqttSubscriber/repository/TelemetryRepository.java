package com.kernelx.mqttSubscriber.repository;


import com.kernelx.mqttSubscriber.entity.Telemetry;
import com.kernelx.mqttSubscriber.entity.TelemetryId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TelemetryRepository
        extends JpaRepository<Telemetry, TelemetryId> {
}
