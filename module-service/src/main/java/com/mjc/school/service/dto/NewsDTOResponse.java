package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public record NewsDTOResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createDate,
        LocalDateTime lastUpdatedDate,
        Long authorId
) {
}