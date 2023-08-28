package com.mjc.school.controller.commands.impl.newsCommand;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("19")
public class ReadNewsByTitleCommand implements Command {
    @Autowired
    @Qualifier("newsController")
    private BaseController<NewsDTORequest, NewsDTOResponse, Long> controller;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(19);
        System.out.println("Operation: Get news by title.\n" + "Enter news title:");
        String title = scanner.nextLine();
        method.invoke(controller, title);
    }
}
