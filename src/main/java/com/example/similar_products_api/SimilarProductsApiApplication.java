package com.example.similar_products_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Aplicación principal Spring Boot para la prueba técnica Backend Dev.
 * Expone el API REST en el puerto 5000
 * El objetivo es mostrar productos similares, optimizando rendimiento y resiliencia. 
 * Todo segun lo requerido en la prueba técnica. Un saludo <3 Albberca.
 */
public class SimilarProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimilarProductsApiApplication.class, args);
	}

}
