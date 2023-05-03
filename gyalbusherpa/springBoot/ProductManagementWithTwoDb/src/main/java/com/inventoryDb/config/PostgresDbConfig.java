package com.inventoryDb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        basePackages = "com.inventoryDb.repository",
        transactionManagerRef = "secondTransactionManager"
)
public class PostgresDbConfig {
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    // entityManagerFactory
    @Bean(name = "secondEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean;
        bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("com.inventoryDb.model");

        return bean;
    }

    // platformTransactionManager
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }
}
