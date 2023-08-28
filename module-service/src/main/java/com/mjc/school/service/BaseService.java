package com.mjc.school.service;

import com.mjc.school.service.exceptions.*;

import java.util.List;

public interface BaseService<T, R, K> {
    List<R> readAll();

    R readById(K id) throws AuthorIDException, NewsIDException, TagIDException;

    R create(T createRequest) throws AuthorNameException, AuthorIDException, TitleOrContentLengthException, TagNameException;

    R update(T updateRequest) throws AuthorIDException, AuthorNameException, NewsIDException, TitleOrContentLengthException, TagIDException, TagNameException;

    boolean deleteById(K id) throws AuthorIDException, NewsIDException, TagIDException;
}
