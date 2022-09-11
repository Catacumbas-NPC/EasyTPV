package com.apppin.proyectopin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProyectoPinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPinApplication.class, args);
	}

}
