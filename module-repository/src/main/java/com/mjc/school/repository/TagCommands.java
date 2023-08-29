package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.Tag;

import java.util.List;

public interface TagCommands<T, K> {
    List<T> readTagsByNewsId(K id);
}
