package com.aguas.srv_sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PressureDTO {
    private String sensorId;
    private double pressure;
    private LocalDateTime readingDateTime;
}