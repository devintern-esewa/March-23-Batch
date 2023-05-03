package com.example.multipledbconnection.fileDetails.configure;

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
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBeanFD",
        transactionManagerRef = "transactionManagerFD",
        basePackages ="com.example.multipledbconnection.fileDetails.repo"
)
public class ConfigureFD {


    @Autowired
    private Environment environment;

    //data source

    @Bean(name="mdb1DataSource")
    @Primary
    public DataSource mdb1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("mdb1.datasource.jdbc-url"));
        dataSource.setDriverClassName(environment.getProperty("mdb1.datasource.driver-class-name"));
        dataSource.setUsername(environment.getProperty("mdb1.datasource.username"));
        dataSource.setPassword(environment.getProperty("mdb1.datasource.password"));
        return dataSource;
    }

    //EntityManagerFactory
    @Bean(name = "entityManagerFactoryBeanFD")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanFD() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(mdb1DataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create-drop ");
        bean.setJpaPropertyMap(props);

        bean.setPackagesToScan(("com.example.multipledbconnection.fileDetails.model"));

        return bean;
    }


    //PlatformTransactionManager

    @Bean (name="transactionManagerFD")
    @Primary
    public PlatformTransactionManager transactionManagerFD(){
        JpaTransactionManager manager=new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBeanFD().getObject());
        return manager;
    }
}
