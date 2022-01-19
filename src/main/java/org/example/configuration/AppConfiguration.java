package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(value={"org.example"})
@EnableWebMvc
public class AppConfiguration {
    @Bean
    public  static PropertySourcesPlaceholderConfigurer cong(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}