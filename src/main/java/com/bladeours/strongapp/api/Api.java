package com.bladeours.strongapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Api {

	public static void main(String[] args) {
		SpringApplication.run(Api.class, args);
	}

}
