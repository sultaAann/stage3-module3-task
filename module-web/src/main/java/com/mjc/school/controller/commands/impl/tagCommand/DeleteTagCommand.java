package com.mjc.school.controller.commands.impl.tagCommand;


import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("15")
public class DeleteTagCommand implements Command {
    @Autowired
    private BaseController<TagDTORequest, TagDTOResponse, Long> controller;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(15);
        System.out.println("Operation: Delete tag by id.\n" + "Enter tag id:");
        long id = Long.parseLong(scanner.nextLine());
        method.invoke(controller, id);
    }
}
