package com.teruel.feramakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FeramakersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeramakersApplication.class, args);
	}

}
