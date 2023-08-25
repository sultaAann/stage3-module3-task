package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.NewsIDException;
import com.mjc.school.service.exceptions.TitleOrContentLengthException;
import com.mjc.school.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class NewsController implements BaseController<NewsDTORequest, NewsDTOResponse, Long> {
    @Autowired
    private NewsService service;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    @CommandHandler(commandNumber = 1)
    public List<NewsDTOResponse> readAll() {
        service.readAll().forEach(System.out::println);
        return service.readAll();
    }

    @Override
    @CommandHandler(commandNumber = 2)
    public NewsDTOResponse readById(Long id) throws NewsIDException {
        NewsDTOResponse res = service.readById(id);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 3)
    public NewsDTOResponse create(NewsDTORequest createRequest) throws TitleOrContentLengthException {
        NewsDTOResponse res = service.create(createRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 4)
    public NewsDTOResponse update(NewsDTORequest updateRequest) throws NewsIDException, TitleOrContentLengthException {
        NewsDTOResponse res = service.update(updateRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 5)
    public boolean deleteById(Long id) throws NewsIDException {
        Boolean res = service.deleteById(id);
        System.out.println(res);
        return res;
    }

    public void deleteRelatedNews(Long id) {
        System.out.println("""
                Delete all articles related to this Author? (Write Number)
                1. Yes
                2. No (Field authorId of related news will be null)""");
        boolean bool = scanner.nextLine().trim().equals("1");
        if (bool) {
            service.readAll().forEach(newsDTOResponse -> {
                if (newsDTOResponse.authorId() == id) {
                    try {
                        service.deleteById(newsDTOResponse.id());
                    } catch (NewsIDException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            System.out.println("All related news have been deleted.");
        } else {
            service.readAll().forEach(newsDTOResponse -> {
                if (newsDTOResponse.authorId() == id) {
                    try {
                        service.update(new NewsDTORequest(newsDTOResponse.id(), newsDTOResponse.title(), newsDTOResponse.content(), null));
                    } catch (TitleOrContentLengthException | NewsIDException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            System.out.println("All related authorId fields have been replaced with null.");
        }
    }
}
