package com.rollerspeed.rollerspeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "Roller Speed API",
    version = "1.0",
    description = "Documentación de la API para la gestión de la escuela Roller Speed"
))
public class RollerspeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollerspeedApplication.class, args);
	}

}
