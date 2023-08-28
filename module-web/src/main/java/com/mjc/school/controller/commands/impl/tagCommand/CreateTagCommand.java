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


@Component("13")
public class CreateTagCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private BaseController<TagDTORequest, TagDTOResponse, Long> controller;

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(13);
        System.out.println("Operation: Create tag. \n" + "Enter Tag's name:");
        String name = scanner.nextLine();
        method.invoke(controller, new TagDTORequest(null, name));

    }
}
