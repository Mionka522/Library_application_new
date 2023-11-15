package com.my.app.util;


import com.my.app.model.Person;
import com.my.app.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
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
        //Посмотреть, есть ли уже человек с таким ФИО
        if(peopleService.findByFIO(person.getFIO()).isPresent()) {
            errors.rejectValue("FIO","","Такой человек уже есть");
        }
    }
}
