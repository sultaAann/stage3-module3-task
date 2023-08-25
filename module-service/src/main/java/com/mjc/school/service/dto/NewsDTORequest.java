package com.mjc.school.service.dto;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public record NewsDTORequest (
        Long id,
        String title,
        String content,
        Long authorId
) {
}