package com.laboratorio.CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Punto de entrada principal para la aplicación Spring Boot CRUD.
 * * <p>
 *  * La anotación {@code @ComponentScan("com.laboratorio.CRUD")} explícitamente le dice a Spring que busque componentes dentro del paquete
 *  * {@code com.laboratorio.CRUD}. Aunque {@code @SpringBootApplication} ya incluye un escaneo del paquete actual, esta línea asegura
 *  * que todos los componentes dentro de tu estructura de paquetes sean detectados.
 */
@SpringBootApplication
@ComponentScan("com.laboratorio.CRUD")
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
