package com.mjc.school;

import com.mjc.school.controller.commands.impl.authorCommand.*;
import com.mjc.school.controller.commands.impl.newsCommand.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    private static final String COMMANDS = """
            Enter the number of operation:
            1 - Get all news.
            2 - Get news by id.
            3 - Create news.
            4 - Update news.
            5 - Remove news by id.
            6 - Get all author.
            7 - Get authors by id.
            8 - Create author.
            9 - Update author.
            10 - Remove author by id.
            0 - Exit.
            """;

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(COMMANDS);


                int num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1 -> context.getBean("1", ReadAllNewsCommand.class).execute();
                    case 2 -> context.getBean("2", ReadByIdNewsCommand.class).execute();
                    case 3 -> context.getBean("3", CreateNewsCommand.class).execute();
                    case 4 -> context.getBean("4", UpdateNewsCommand.class).execute();
                    case 5 -> context.getBean("5", DeleteNewsCommand.class).execute();
                    case 6 -> context.getBean("6", ReadAllAuthorCommand.class).execute();
                    case 7 -> context.getBean("7", ReadByIdAuthorCommand.class).execute();
                    case 8 -> context.getBean("8", CreateAuthorCommand.class).execute();
                    case 9 -> context.getBean("9", UpdateAuthorCommand.class).execute();
                    case 10 -> context.getBean("10", DeleteAuthorCommand.class).execute();
                    case 0 -> System.exit(1);
                }

            } catch (RuntimeException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}