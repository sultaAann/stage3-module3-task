package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import com.mjc.school.service.exceptions.*;
import com.mjc.school.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDTORequest, TagDTOResponse, Long> {

    @Autowired
    private TagService service;

    @Override
    @CommandHandler(commandNumber = 11)
    public List<TagDTOResponse> readAll() {
        List<TagDTOResponse> res = service.readAll();
        res.forEach(System.out::println);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 12)
    public TagDTOResponse readById(Long id) throws AuthorIDException, NewsIDException, TagIDException {
        TagDTOResponse res = service.readById(id);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 13)
    public TagDTOResponse create(TagDTORequest createRequest) throws AuthorNameException, AuthorIDException, TagNameException, TitleOrContentLengthException {
        TagDTOResponse res = service.create(createRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 14)
    public TagDTOResponse update(TagDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagNameException, NewsIDException, TagIDException, TitleOrContentLengthException {
        TagDTOResponse res = service.update(updateRequest);
        System.out.println(res);
        return res;
    }

    @Override
    @CommandHandler(commandNumber = 15)
    public boolean deleteById(Long id) throws AuthorIDException, NewsIDException, TagIDException {
        return service.deleteById(id);
    }

    @CommandHandler(commandNumber = 21)
    public List<TagDTOResponse> readTagsByNewsId(Long id) {
        service.readTagsByNewsId(id).forEach(System.out::println);
        return service.readTagsByNewsId(id);
    }
}
