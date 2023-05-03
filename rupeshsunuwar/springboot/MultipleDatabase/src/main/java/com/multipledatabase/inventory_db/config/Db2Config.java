package com.multipledatabase.inventory_db.config;


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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        basePackages = {"com.multipledatabase.inventory_db.repository"}, transactionManagerRef = "secondTransactionManager")
public class Db2Config {


    @Autowired
    private Environment environment;


    @Bean("secondDataSource")
    @Primary
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("spring.db2.datasource.jdbc-url"));
        dataSource.setDriverClassName(environment.getProperty("spring.db2.datasource.drive-class-name"));
        dataSource.setUsername(environment.getProperty("spring.db2.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.db2.datasource.password"));

        return dataSource;


    }

    @Primary
    @Bean(name = "secondEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

        bean.setDataSource((dataSource()));
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.show_sql", "true");


        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("com.multipledatabase.inventory_db.entity");


        return bean;

    }

    @Primary
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager() {


        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());


        return manager;
    }


}
