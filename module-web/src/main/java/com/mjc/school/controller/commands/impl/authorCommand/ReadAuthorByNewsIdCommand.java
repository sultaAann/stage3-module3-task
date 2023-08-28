package com.mjc.school.controller.commands.impl.authorCommand;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("22")
public class ReadAuthorByNewsIdCommand implements Command {
    @Autowired
    private BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(22);
        System.out.println("Operation: Get authors by news id.\n" + "Enter news id:");
        long id = Long.parseLong(scanner.nextLine());
        method.invoke(controller, id);
    }
}
