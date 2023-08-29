package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.NewsCommands;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsCommandsService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.NewsIDException;
import com.mjc.school.service.exceptions.TitleOrContentLengthException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsDTORequest, NewsDTOResponse, Long>, NewsCommandsService<NewsDTOResponse, Long> {
    @Autowired
    private NewsCommands<News, Long> newsCommands;
    @Autowired
    private BaseRepository<News, Long> repository;

    @Override
    public List<NewsDTOResponse> readAll() {
        return repository.readAll().stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public NewsDTOResponse readById(Long id) throws NewsIDException {
        Validator.newsIdValidator(String.valueOf(id));
        if (repository.readById(id).isPresent()) {
            return NewsMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest createRequest) throws TitleOrContentLengthException {
        Validator.titleAndContentValidate(createRequest.title(), createRequest.content());
        News model = NewsMapper.INSTANCE.dtoToModel(createRequest);

        Author author = new Author();
        author.setId(createRequest.authorId());
        model.setAuthorId(author);

        repository.create(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest updateRequest) throws NewsIDException, TitleOrContentLengthException {
        Validator.newsIdValidator(String.valueOf(updateRequest.id()));
        Validator.titleAndContentValidate(updateRequest.title(), updateRequest.content());
        News model = NewsMapper.INSTANCE.dtoToModel(updateRequest);

        Author author = new Author();
        author.setId(updateRequest.authorId());
        model.setAuthorId(author);

        repository.update(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) throws NewsIDException {
        Validator.newsIdValidator(String.valueOf(id));
        return repository.deleteById(id);
    }

    @Override
    public List<NewsDTOResponse> readByTagName(String tagName) {
        return newsCommands.readByTagName(tagName).stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public List<NewsDTOResponse> readByTagId(Long tagId) {
        return newsCommands.readByTagId(tagId).stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public List<NewsDTOResponse> readByAuthorName(String authorName) {
        return newsCommands.readByAuthorName(authorName).stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public List<NewsDTOResponse> readByTitle(String title) {
        return newsCommands.readByTitle(title).stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public List<NewsDTOResponse> readByContent(String content) {
        return newsCommands.readByContent(content).stream()
                .map(NewsMapper.INSTANCE::modelToDto)
                .toList();
    }
}
