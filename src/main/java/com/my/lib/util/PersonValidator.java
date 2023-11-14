package com.my.lib.util;

import com.my.lib.model.Person;
import com.my.lib.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator( PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    //На каких объектах какого класса будем использовать этот валидатор ( одна сущность для валидатора)
    public boolean supports(Class<?> aClass) {
        return Person.class.equals((aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //Посмотреть, есть ли человек с таким же емайлом
        if(peopleService.getFIOval(person.getFIO()).isPresent()) {
            errors.rejectValue("FIO","","Такой человек уже есть");
        }
    }
}
