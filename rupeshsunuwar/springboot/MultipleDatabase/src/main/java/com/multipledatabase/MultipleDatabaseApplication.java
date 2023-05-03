package com.multipledatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MultipleDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatabaseApplication.class, args);
    }

}
