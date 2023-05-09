package com.example.nodbtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NoDbTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoDbTaskApplication.class, args);
	}

}
