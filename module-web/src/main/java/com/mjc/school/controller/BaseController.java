package com.mjc.school.controller;

import com.mjc.school.service.exceptions.AuthorIDException;
import com.mjc.school.service.exceptions.AuthorNameException;
import com.mjc.school.service.exceptions.NewsIDException;
import com.mjc.school.service.exceptions.TitleOrContentLengthException;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll();

    R readById(K id) throws AuthorIDException, NewsIDException;

    R create(T createRequest) throws AuthorNameException, AuthorIDException, TitleOrContentLengthException;

    R update(T updateRequest) throws AuthorIDException, AuthorNameException, NewsIDException, TitleOrContentLengthException;

    boolean deleteById(K id) throws AuthorIDException, NewsIDException;
}
