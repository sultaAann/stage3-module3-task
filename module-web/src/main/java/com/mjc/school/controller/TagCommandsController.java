package com.mjc.school.controller;

import java.util.List;

public interface TagCommandsController<T, K> {
    List<T> readTagsByNewsId(K id);
}
