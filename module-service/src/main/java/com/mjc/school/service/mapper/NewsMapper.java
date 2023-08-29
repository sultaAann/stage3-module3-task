package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.News;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "authorId", source = "authorId.id")
    NewsDTOResponse modelToDto(News model);

    @Mapping(target = "authorId.id", source = "authorId")
    News dtoToModel(NewsDTORequest modelDTO);

}
