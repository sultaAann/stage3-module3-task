package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTOResponse modelToDto(AuthorModel model);

    AuthorModel dtoToModel(AuthorDTORequest modelDTO);
}
