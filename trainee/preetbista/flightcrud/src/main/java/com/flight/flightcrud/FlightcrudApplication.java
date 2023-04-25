package com.flight.flightcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FlightcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightcrudApplication.class, args);
    }

}
