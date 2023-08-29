package com.mjc.school.repository;

import java.util.List;

public interface AuthorCommands<T, K> {
    List<T> readAuthorByNewsId(K id);

}
