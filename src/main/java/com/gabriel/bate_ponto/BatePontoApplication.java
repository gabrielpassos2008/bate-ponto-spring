package com.gabriel.bate_ponto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatePontoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatePontoApplication.class, args);
	}

}
