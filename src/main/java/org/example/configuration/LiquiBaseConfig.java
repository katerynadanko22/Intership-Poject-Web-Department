package org.example.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(value = {"org.example"})
public class LiquiBaseConfig {

    @Autowired
    HibernateConfig hibernateConfig;
    public static final String CLASSPATH_CHANGELOG = "classpath:changelog.xml";

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(CLASSPATH_CHANGELOG);
        liquibase.setDataSource(hibernateConfig.getDataSource());
        return liquibase;
    }
}
