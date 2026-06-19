package com.kernelx.mqttSubscriber.service;

import com.kernelx.mqttSubscriber.entity.Telemetry;
import com.kernelx.mqttSubscriber.repository.TelemetryRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryRepository repository;

    // Thread-safe queue
    private final Queue<Telemetry> buffer = new ConcurrentLinkedQueue<>();

    // Called from MQTT thread
    public void addToBuffer(Telemetry telemetry) {
        buffer.add(telemetry);
    }

    // Runs every 2 minutes
    @Scheduled(fixedRate = 8000) // 30,000 ms = 0.5 minutes
    @Transactional
    public void flushBuffer() {

        if (buffer.isEmpty()) {
            return;
        }

        List<Telemetry> batch = new ArrayList<>();

        Telemetry item;
        while ((item = buffer.poll()) != null) {
            batch.add(item);
        }

        repository.saveAll(batch);

        System.out.println("Inserted batch of size: " + batch.size());
    }

    @PreDestroy
    @Transactional
    public void shutdownFlush() {
        System.out.println("Application shutting down. Flushing remaining telemetry...");
        flushBuffer();
    }
}

