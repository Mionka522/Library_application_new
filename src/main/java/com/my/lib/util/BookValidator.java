package com.my.lib.util;

import com.my.lib.model.Book;
import com.my.lib.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    //На каких объектах какого класса будем использовать этот валидатор ( одна сущность для валидатора)
    public boolean supports(Class<?> aClass) {
        return Book.class.equals((aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        //Посмотреть, есть книга с таким названием уже в списке
        if(bookService.getTitleVal(book.getTitle()).isPresent()) {
            errors.rejectValue("title","","Такая книга уже есть");
        }

    }
}
