package com.gestao.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gestao.biblioteca")
public class GestaoBibliotecaApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestaoBibliotecaApplication.class, args);
	}
}