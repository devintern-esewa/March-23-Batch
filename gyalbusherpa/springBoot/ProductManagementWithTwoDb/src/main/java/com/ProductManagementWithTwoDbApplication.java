package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ProductManagementWithTwoDbApplication extends SpringBootServletInitializer {

    private static Properties getProps() {
        Properties properties = new Properties();
        properties.put("spring.config.location", "file:${catalina.home}/conf/product_application.properties");
        return properties;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProductManagementWithTwoDbApplication.class)
                .properties(getProps());
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProductManagementWithTwoDbApplication.class)
                .properties(getProps())
                .run(args);
    }

}
