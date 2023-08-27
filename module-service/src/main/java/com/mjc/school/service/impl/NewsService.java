package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
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
public class NewsService implements BaseService<NewsDTORequest, NewsDTOResponse, Long> {
    @Autowired
    private BaseRepository<NewsModel, Long> repository;

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
        NewsModel model = NewsMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest updateRequest) throws NewsIDException, TitleOrContentLengthException {
        Validator.newsIdValidator(String.valueOf(updateRequest.id()));
        Validator.titleAndContentValidate(updateRequest.title(), updateRequest.content());
        NewsModel model = NewsMapper.INSTANCE.dtoToModel(updateRequest);
        repository.update(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) throws NewsIDException {
        Validator.newsIdValidator(String.valueOf(id));
        return repository.deleteById(id);
    }
}
