package org.example;

import org.example.config.SpringConfig;
import org.example.controller.StartMenuController;
import org.example.repository.TakenBookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
        StartMenuController startMenuController=(StartMenuController) context.getBean("startMenuController");
        startMenuController.start();

    }
}