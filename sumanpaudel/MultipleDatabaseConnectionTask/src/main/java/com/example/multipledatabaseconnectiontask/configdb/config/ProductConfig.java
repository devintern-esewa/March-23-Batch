package com.example.multipledatabaseconnectiontask.configdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = {"com.example.multipledatabaseconnectiontask.configdb.repo"},
        transactionManagerRef = "transactionManager"
)
public class ProductConfig {
    @Autowired
    private Environment environment;
    @Bean
    @Primary
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
    @Bean(name = "entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setDataSource(dataSource());
        JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        localEntityManagerFactoryBean.setJpaVendorAdapter(adapter);
        Map<String,String> map=new HashMap<>();
        map.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        map.put("hibernate.showSql","true");
        map.put("hibernate.hbm2ddl.auto","create-drop");
        localEntityManagerFactoryBean.setJpaPropertyMap(map);
        localEntityManagerFactoryBean.setPackagesToScan("com.example.multipledatabaseconnectiontask.configdb.model");
        return localEntityManagerFactoryBean;
    }
    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager=new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(localEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
