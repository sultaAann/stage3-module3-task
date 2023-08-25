package com.mjc.school.controller.commands;

import java.lang.reflect.InvocationTargetException;

public interface Command {
    void execute() throws InvocationTargetException, IllegalAccessException;
}
