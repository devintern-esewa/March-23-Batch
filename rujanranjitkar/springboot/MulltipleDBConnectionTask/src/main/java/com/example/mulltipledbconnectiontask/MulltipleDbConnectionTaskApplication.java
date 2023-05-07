package com.example.mulltipledbconnectiontask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MulltipleDbConnectionTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MulltipleDbConnectionTaskApplication.class, args);
    }

}
