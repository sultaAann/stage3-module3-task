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

@Component("3")
public class CreateNewsCommand implements Command {
    @Autowired
    @Qualifier("newsController")
    private BaseController<NewsDTORequest, NewsDTOResponse, Long> controller;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(3);
        System.out.println("Operation: Create news.\n" + "Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author ID");
        Long authorID = Long.parseLong(scanner.nextLine());
        method.invoke(controller, new NewsDTORequest(null, title, content, authorID));
    }
}
