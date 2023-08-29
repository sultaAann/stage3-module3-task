package com.mjc.school.service;

import java.util.List;

public interface TagCommandsService<T, K> {
    List<T> readTagsByNewsId(K id);
}
