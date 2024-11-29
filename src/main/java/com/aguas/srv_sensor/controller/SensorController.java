package com.aguas.srv_sensor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.aguas.srv_sensor.dto.PressureDTO;
import com.aguas.srv_sensor.service.SensorService;

@RestController
public class SensorController {

    private static final Logger logger = LoggerFactory.getLogger(SensorController.class);
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // Método agendado para executar a cada 5 segundos
    @Scheduled(fixedRate = 5000)
    public void sendPressureReadings() {
        logger.info("Iniciando leitura do sensor");
        PressureDTO pressureDTO = sensorService.readSensorData();
        logger.info("Leitura do sensor: {}", pressureDTO);
        sensorService.sendDataToBackend(pressureDTO);
        logger.info("Dados enviados com sucesso para o backend");
    }
}