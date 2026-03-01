package com.kernelx.mqttSubscriber.mqtt;

import com.kernelx.mqttSubscriber.config.MqttProperties;
import com.kernelx.mqttSubscriber.entity.Telemetry;
import com.kernelx.mqttSubscriber.entity.TelemetryId;
import com.kernelx.mqttSubscriber.entity.dto.TelemetryDTO;
import com.kernelx.mqttSubscriber.service.TelemetryService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class MqttSubscriber {

    private final MqttProperties properties;
    private final TelemetryService telemetryService;
    private final ObjectMapper objectMapper;
    private final Validator validator;


    @PostConstruct
    public void init() throws MqttException {

        MqttClient client = new MqttClient(
                properties.broker,
                properties.clientId,
                new MemoryPersistence()
        );

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);

        client.connect(options);

        client.subscribe(properties.topic, properties.qos, this::handleMessage);
        System.out.println("MQTT connected and subscribed");
    }

    private void handleMessage(String topic, MqttMessage message) {

        try {
            String payload = new String(message.getPayload());
            System.out.println("Received: " + payload);

            TelemetryDTO dto = objectMapper.readValue(payload, TelemetryDTO.class);

            Set<ConstraintViolation<TelemetryDTO>> violations = validator.validate(dto);

            if (!violations.isEmpty()) {
                violations.forEach(v ->
                        log.warn("Field: {} error: {} , Sensor id: {} Recorded Timestamp: {} ",
                                v.getPropertyPath(),
                                v.getMessage(),
                                dto.getDeviceId(),
                                dto.getTimestamp())
                );
                return; // stop processing the msg
            }

            TelemetryId id = new TelemetryId(
                    dto.getDeviceId(),
                    dto.getTimestamp()
            );

            Telemetry telemetry = new Telemetry();
            telemetry.setId(id);
            telemetry.setValue(dto.getValue());
            telemetry.setSensorHealth(dto.getSensorHealth());

            telemetryService.addToBuffer(telemetry);

        } catch (Exception e) {
            log.error("Failed to process MQTT message", e);
            e.printStackTrace();
        }
    }
}
