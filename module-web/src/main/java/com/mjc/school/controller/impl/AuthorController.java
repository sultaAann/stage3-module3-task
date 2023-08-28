package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.exceptions.*;
import com.mjc.school.service.impl.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDTORequest, AuthorDTOResponse, Long> {
    @Autowired
    private AuthorService service;

    @Autowired
    private NewsController newsController;

    @Override
    @CommandHandler(commandNumber = 6)
    public List<AuthorDTOResponse> readAll() {
        service.readAll().forEach(System.out::println);
        return service.readAll();
    }

    @Override
    @CommandHandler(commandNumber = 7)
    public AuthorDTOResponse readById(Long id) throws AuthorIDException, NewsIDException, TagIDException {
        AuthorDTOResponse res = service.readById(id);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 8)
    public AuthorDTOResponse create(AuthorDTORequest createRequest) throws AuthorNameException, AuthorIDException, TagNameException, TitleOrContentLengthException {
        AuthorDTOResponse res = service.create(createRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 9)
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagNameException, NewsIDException, TagIDException, TitleOrContentLengthException {
        AuthorDTOResponse res = service.update(updateRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 10)
    public boolean deleteById(Long id) throws AuthorIDException, NewsIDException, TagIDException {
        newsController.deleteRelatedNews(id);
        return service.deleteById(id);
    }

    @CommandHandler(commandNumber = 22)
    public List<AuthorDTOResponse> readAuthorByNewsId(Long id) {
        service.readAuthorByNewsId(id).forEach(System.out::println);
        return service.readAuthorByNewsId(id);
    }
}
