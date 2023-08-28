package com.mjc.school.controller;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.impl.TagController;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Component
public class MessageHandler {

    private static final Map<Integer, Method> COMMANDS = new HashMap<>();

    static {
        readAllNewsCommands();
        readAllAuthorCommands();
        readAllTagCommands();
    }

    public static Method get(int num) {
        return COMMANDS.get(num);
    }

    private static void readAllNewsCommands() {
        Class<NewsController> annotatedMethodsClass = NewsController.class;

        for (Method method : annotatedMethodsClass.getDeclaredMethods()) {


            Annotation annotation = method.getAnnotation(CommandHandler.class);
            if (annotation != null){
                CommandHandler command = (CommandHandler) annotation;
                COMMANDS.put(command.commandNumber(), method);
            }
        }
    }
    private static void readAllAuthorCommands() {
        Class<AuthorController> annotatedMethodsClass1 = AuthorController.class;

        for (Method method : annotatedMethodsClass1.getDeclaredMethods()) {

            Annotation annotation = method.getAnnotation(CommandHandler.class);
            if (annotation != null) {
                CommandHandler command1 = (CommandHandler) annotation;
                COMMANDS.put(command1.commandNumber(), method);
            }
        }
    }

    private static void readAllTagCommands() {
        Class<TagController> annotatedMethodsClass1 = TagController.class;

        for (Method method : annotatedMethodsClass1.getDeclaredMethods()) {

            Annotation annotation = method.getAnnotation(CommandHandler.class);
            if (annotation != null) {
                CommandHandler command1 = (CommandHandler) annotation;
                COMMANDS.put(command1.commandNumber(), method);
            }
        }
    }
}
