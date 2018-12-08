package com.lombardi.fuelmng.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.lombardi.fuelmng.model")
@ComponentScan(basePackages = "com.lombardi.fuelmng")
@EnableJpaRepositories("com.lombardi.fuelmng.repo")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
