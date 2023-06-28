package org.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "org.example")
public class SpringConfig {
    @Bean
public Scanner scanner(){
        return new Scanner(System.in);
    }
    @Bean
    public Scanner scannerLine(){
        return new Scanner(System.in);
    }

    @Bean
    public SessionFactory factory(){
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata=new MetadataSources(ssr).getMetadataBuilder().build();
    return metadata.getSessionFactoryBuilder().build();
    }
}
