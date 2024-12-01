package com.aguas.srv_sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
public class SrvSensorApplication {

	private static final Logger logger = LoggerFactory.getLogger(SrvSensorApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando aplicação...");
		Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
            logger.debug("Carregando variável de ambiente: {}", entry.getKey());
        });
		logger.info("Iniciando Spring Application...");
		SpringApplication.run(SrvSensorApplication.class, args);
		logger.info("Aplicação iniciada com sucesso!");
	}

}