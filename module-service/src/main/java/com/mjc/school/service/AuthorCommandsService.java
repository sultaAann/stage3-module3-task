package com.mjc.school.service;

import java.util.List;

public interface AuthorCommandsService<T, K> {
    List<T> readAuthorByNewsId(K id);
}
