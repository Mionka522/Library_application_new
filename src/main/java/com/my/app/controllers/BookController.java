package com.my.app.controllers;


import com.my.app.model.Book;
import com.my.app.model.Person;
import com.my.app.services.BookService;
import com.my.app.services.PeopleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;


    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;

    }


    @GetMapping()
    public String index(Model model) {
        //показать весь список
        model.addAttribute("book", bookService.findAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    //страница конкретной книги
    public String show(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));

        Person bookOwner = bookService.getBookOwner(id);

        if(bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people",peopleService.findAll());
        return "book/show";
    }

    @GetMapping("/new")
    //страница добавления новой книги
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    //добавление в список
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/new";

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    //редактирование информации о книге
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    //обновление данных о книге
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "book/edit";

        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    //Удаление книги
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/release")
    //Освободить кнгиу
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/book/"+id;
    }
    @PatchMapping("/{id}/assign")
    //Назначить владельца
    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id,selectedPerson);
        return "redirect:/book/" + id;
    }
}