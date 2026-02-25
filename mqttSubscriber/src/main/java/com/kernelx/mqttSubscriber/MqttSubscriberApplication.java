package com.kernelx.mqttSubscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MqttSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttSubscriberApplication.class, args);
	}

}
