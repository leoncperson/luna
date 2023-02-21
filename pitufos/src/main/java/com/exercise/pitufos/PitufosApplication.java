package com.exercise.pitufos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan("com.exercise.pitufos")
public class PitufosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitufosApplication.class, args);
	}

}
