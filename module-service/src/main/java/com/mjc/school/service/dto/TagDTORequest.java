package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public record TagDTORequest(
        Long id,
        String name
) {
}