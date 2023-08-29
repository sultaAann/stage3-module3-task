package com.mjc.school.service.impl;

import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.exceptions.AuthorIDException;
import com.mjc.school.service.exceptions.AuthorNameException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDTORequest, AuthorDTOResponse, Long> {
    @Autowired
    private AuthorRepository repository;

    @Override
    public List<AuthorDTOResponse> readAll() {
        return repository.readAll().stream()
                .map(AuthorMapper.INSTANCE::modelToDto)
                .toList();
    }

    @Override
    public AuthorDTOResponse readById(Long id) throws AuthorIDException {
        Validator.authorIdValidator(String.valueOf(id));
        if (repository.readById(id).isPresent()) {
            return AuthorMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) throws AuthorNameException {
        Validator.authorNameValidator(createRequest.name());
        Author model = AuthorMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return AuthorMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) throws AuthorIDException, AuthorNameException {
        Validator.authorIdValidator(String.valueOf(updateRequest.id()));
        Validator.authorNameValidator(updateRequest.name());
        Author model = AuthorMapper.INSTANCE.dtoToModel(updateRequest);
        repository.update(model);
        return AuthorMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) throws AuthorIDException {
        Validator.authorIdValidator(String.valueOf(id));
        return repository.deleteById(id);
    }

    public List<AuthorDTOResponse> readAuthorByNewsId(Long id) {
        return repository.readAuthorByNewsId(id).stream()
                .map(AuthorMapper.INSTANCE::modelToDto)
                .toList();
    }
}
