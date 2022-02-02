package org.example.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(value = {"org.example"})
public class TestConfig {

    @Autowired
    public TestConfig(Environment env) {
        this.env = env;
    }
    private final Environment env;

    public static final String ENTITY_BASE_PACKAGE = "org.example.entity";
    public static final String DATASOURCE_DRIVER = "datasource.driver.test";
    public static final String DATASOURCE_URL = "datasource.url.test";
    public static final String DATASOURCE_USERNAME = "datasource.username";
    public static final String DATASOURCE_PASSWORD = "datasource.password";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect.test";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_BATCH_SIZE = "hibernate.batch.size";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto.test";
    public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current.session.context.class";

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DATASOURCE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(DATASOURCE_URL));
        dataSource.setUsername(env.getRequiredProperty(DATASOURCE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(DATASOURCE_PASSWORD));
        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog-0.1.xml");
        liquibase.setDataSource(getDataSource());
        return liquibase;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(ENTITY_BASE_PACKAGE);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty(HIBERNATE_BATCH_SIZE));
        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS));
        return properties;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(ENTITY_BASE_PACKAGE);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());
        return em;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
