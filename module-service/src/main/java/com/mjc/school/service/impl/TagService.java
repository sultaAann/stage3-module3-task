package com.mjc.school.service.impl;

import com.mjc.school.repository.entity.impl.Tag;
import com.mjc.school.repository.implRepo.TagRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import com.mjc.school.service.exceptions.AuthorIDException;
import com.mjc.school.service.exceptions.AuthorNameException;
import com.mjc.school.service.exceptions.TagIDException;
import com.mjc.school.service.exceptions.TagNameException;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<TagDTORequest, TagDTOResponse, Long> {

    @Autowired
    private TagRepository repository;

    @Override
    public List<TagDTOResponse> readAll() {
        return repository.readAll().stream()
                .map(TagMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public TagDTOResponse readById(Long id) throws TagIDException {
        Validator.tagIdValidator(String.valueOf(id));
        if (repository.readById(id).isPresent()) {
            return TagMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public TagDTOResponse create(TagDTORequest createRequest) throws AuthorNameException, TagNameException {
        Validator.tagNameValidator(createRequest.name());
        Tag model = TagMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return TagMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public TagDTOResponse update(TagDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagIDException, TagNameException {
        Validator.tagIdValidator(String.valueOf(updateRequest.id()));
        Validator.tagNameValidator(updateRequest.name());
        Tag model = TagMapper.INSTANCE.dtoToModel(updateRequest);
        repository.update(model);
        return TagMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) throws AuthorIDException, TagIDException {
        Validator.tagIdValidator(String.valueOf(id));
        return repository.deleteById(id);
    }

    public List<TagDTOResponse> readTagsByNewsId(Long id) {
        return repository.readTagsByNewsId(id).stream()
                .map(TagMapper.INSTANCE::modelToDto)
                .toList();
    }
}
