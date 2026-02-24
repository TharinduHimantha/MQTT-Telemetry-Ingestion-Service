package com.kernelx.mqttSubscriber.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttProperties {

    @Value("${mqtt.broker}")
    public String broker;

    @Value("${mqtt.client-id}")
    public String clientId;

    @Value("${mqtt.topic}")
    public String topic;

    @Value("${mqtt.qos}")
    public int qos;
}
