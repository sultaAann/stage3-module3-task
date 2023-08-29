package com.mjc.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... args) throws Exception {
        var inputSystem = context.getBean("inputSystem", InputSystem.class);
        inputSystem.start();
    }
}