package com.mjc.school.controller;

import com.mjc.school.service.exceptions.*;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll();

    R readById(K id) throws AuthorIDException, NewsIDException, TagIDException;

    R create(T createRequest) throws AuthorNameException, AuthorIDException, TitleOrContentLengthException, TagNameException;

    R update(T updateRequest) throws AuthorIDException, AuthorNameException, NewsIDException, TitleOrContentLengthException, TagNameException, TagIDException;

    boolean deleteById(K id) throws AuthorIDException, NewsIDException, TagIDException;
}
