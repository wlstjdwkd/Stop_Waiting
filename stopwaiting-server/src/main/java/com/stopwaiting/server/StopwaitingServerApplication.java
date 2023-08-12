package com.stopwaiting.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StopwaitingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StopwaitingServerApplication.class, args);
	}

}
