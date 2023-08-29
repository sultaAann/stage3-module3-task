package com.mjc.school.repository;



import java.util.List;

public interface NewsCommands<T, K> {
    List<T> readByTagName(String tagName);

    List<T> readByTagId(K tagId);

    List<T> readByAuthorName(String authorName);

    List<T> readByTitle(String title);

    List<T> readByContent(String content);
}
