package com.my.lib.controllers;


import com.my.lib.model.Book;
import com.my.lib.model.Person;
import com.my.lib.services.BookService;
import com.my.lib.services.PeopleService;
import com.my.lib.util.BookValidator;
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
    private final BookValidator bookValidator;

    @Autowired
    public BookController( BookService bookService, PeopleService peopleService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookService.findAll());
        return "book/index";
    }

    @GetMapping("/{title}")
    public String show(@PathVariable("title") String title, Model model,@ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(title));

        Optional<Person> bookOwner = bookService.getBookOwner(title);

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people",peopleService.findAll());
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book,bindingResult);
        if (bindingResult.hasErrors())
            return "book/new";

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{title}/edit")
    public String edit(Model model, @PathVariable("title") String title) {
        model.addAttribute("book", bookService.findOne(title));
        return "book/edit";
    }

    @PatchMapping("/{title}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("title") String title) {
        bookValidator.validate(book,bindingResult);

        if (bindingResult.hasErrors())
            return "book/edit";

        bookService.update(title, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{title}")
    public String delete(@PathVariable("title") String title) {
        bookService.delete(title);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/book/"+id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id,selectedPerson);
        return "redirect:/book/" + id;
    }

}