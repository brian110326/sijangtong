package com.example.sijangtong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing
public class SijangtongApplication {

	public static void main(String[] args) {
		SpringApplication.run(SijangtongApplication.class, args);
	}

}
