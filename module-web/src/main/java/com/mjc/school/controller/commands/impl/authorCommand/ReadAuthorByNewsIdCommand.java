package com.mjc.school.controller.commands.impl.authorCommand;

import com.mjc.school.controller.AuthorCommandsController;
import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("22")
public class ReadAuthorByNewsIdCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private AuthorCommandsController<AuthorDTOResponse, Long> controller;

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(22);
        System.out.println("Operation: Get authors by news id.\n" + "Enter news id:");
        long id = Long.parseLong(scanner.nextLine());
        method.invoke(controller, id);
    }
}
