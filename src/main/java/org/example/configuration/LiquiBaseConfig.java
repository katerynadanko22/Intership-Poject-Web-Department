package org.example.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(value = {"org.example"})
public class LiquiBaseConfig {

    private final HibernateConfig hibernateConfig;

    @Autowired
    public LiquiBaseConfig(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog.xml");
        liquibase.setDataSource(hibernateConfig.getDataSource());
        return liquibase;
    }
}
