package com.mjc.school.service.mapper;

import com.mjc.school.repository.entity.impl.News;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTOResponse modelToDto(News model);

    News dtoToModel(NewsDTORequest modelDTO);

}
