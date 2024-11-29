package com.aguas.srv_sensor.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aguas.srv_sensor.dto.PressureDTO;

@Service
public class SensorService {

    private static final Logger logger = LoggerFactory.getLogger(SensorService.class);

    private final WebClient webClient;

    @Value("${sensor.id}")
    private String sensorId;

    @Value("${backend.api.url}")
    private String backendApiUrl;

    private final Random random = new Random();

    public SensorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        logger.info("SensorService initialized with sensorId: {}", sensorId);
    }

    public PressureDTO readSensorData() {
        logger.debug("Reading sensor data...");
        double pressure = 10 + (90 * random.nextDouble()); // Valor entre 10 e 100
        PressureDTO pressureDTO = new PressureDTO(sensorId, pressure, LocalDateTime.now());
        logger.info("Sensor reading completed. Pressure: {} for sensor: {}", pressure, sensorId);
        return pressureDTO;
    }

    public void sendDataToBackend(PressureDTO pressureDTO) {
        logger.debug("Sending data to backend: {}", pressureDTO);
        webClient.post()
                .uri(backendApiUrl)
                .bodyValue(pressureDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(response -> logger.info("Data sent successfully: {}", pressureDTO))
                .doOnError(error -> logger.error("Error sending data: {}", error.getMessage()))
                .subscribe();
    }
}