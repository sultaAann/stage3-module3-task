package com.mjc.school.controller.commands.impl.authorCommand;


import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("10")
public class DeleteAuthorCommand implements Command {
    @Autowired
    private BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(10);
        System.out.println("Operation: Delete author by id.\n" + "Enter author id:");
        long id = Long.parseLong(scanner.nextLine());
        method.invoke(controller, id);
    }
}
