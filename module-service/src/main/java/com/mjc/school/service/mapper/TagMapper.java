package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.Tag;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTOResponse modelToDto(Tag model);

    Tag dtoToModel(TagDTORequest modelDTO);
}
