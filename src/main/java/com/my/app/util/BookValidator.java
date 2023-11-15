package com.my.app.util;

import com.my.app.services.BookService;

import com.my.app.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookService bookService;

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
        //Посмотреть, есть ли уже книга с таким названием
        if(bookService.getTitleVal(book.getTitle()).isPresent()) {
            errors.rejectValue("title","","Такая книга уже есть");
        }

    }
}
