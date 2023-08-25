package com.mjc.school.service.validator;

import com.mjc.school.service.exceptions.AuthorIDException;
import com.mjc.school.service.exceptions.AuthorNameException;
import com.mjc.school.service.exceptions.NewsIDException;
import com.mjc.school.service.exceptions.TitleOrContentLengthException;

import static com.mjc.school.service.ErrorMessages.*;

public class Validator {

    public static void titleAndContentValidate(String title, String content) throws TitleOrContentLengthException {
        if (title.length() < 5 || title.length() > 30) {
            throw new TitleOrContentLengthException(String.format(ERROR_CODE_2, "title", 30));
        }
        if (content.length() < 5 || content.length() > 255) {
            throw new TitleOrContentLengthException(String.format(ERROR_CODE_2, "content", 255));
        }
    }

    public static void authorIdValidator(String authorId) throws AuthorIDException {
        long id;
        try {
            id = Long.parseLong(authorId);
        } catch (NumberFormatException e) {
            throw new AuthorIDException(ERROR_CODE_5);
        }
        if (id < 0) throw new AuthorIDException(ERROR_CODE_4);
    }

    public static void newsIdValidator(String newsId) throws NewsIDException {
        long id;
        try {
            id = Long.parseLong(newsId);
        } catch (NumberFormatException e) {
            throw new NewsIDException(ERROR_CODE_3);
        }
        if (id < 0) throw new NewsIDException(ERROR_CODE_1);
    }

    public static void authorNameValidator(String authorName) throws AuthorNameException {
        if (authorName.length() < 3 || authorName.length() > 15) {
            throw new AuthorNameException(ERROR_CODE_6);
        }
    }
}
