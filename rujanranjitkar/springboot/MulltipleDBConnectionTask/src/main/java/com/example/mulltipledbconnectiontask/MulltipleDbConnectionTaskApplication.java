package com.example.mulltipledbconnectiontask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MulltipleDbConnectionTaskApplication extends SpringBootServletInitializer {

    private static Properties getProps() {
        Properties properties = new Properties();
        properties.put("spring.config.location", "file:${catalina.home}/conf/multipleDBApplication.properties");
        return properties;
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MulltipleDbConnectionTaskApplication.class)
                .properties(getProps());
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(MulltipleDbConnectionTaskApplication.class)
                .properties(getProps())
                .run(args);
    }
}
