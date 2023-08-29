package com.mjc.school.controller;

import java.util.List;

public interface AuthorCommandsController<T, K> {
    List<T> readAuthorByNewsId(K id);
}
