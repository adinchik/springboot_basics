package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.geekbrains.controllers", "ru.geekbrains.configs", "ru.geekbrains.entities", "ru.geekbrains.repositories", "ru.geekbrains.services"})

public class GeekspringApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeekspringApplication.class, args);
	}
}
