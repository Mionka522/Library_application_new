package com.my.lib.services;

import com.my.lib.model.Book;
import com.my.lib.model.Person;
import com.my.lib.repositories.BookRepository;
import com.my.lib.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(String title) {
        Optional<Book> foundBook = bookRepository.findById(title);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(String title, Book updatedBook) {
        updatedBook.setTitle(title);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(String title) {
        bookRepository.deleteById(title);
    }
    @Transactional
    public Optional<Person> getBookOwner(String title) {
        return bookRepository.findOwner(title);
    }
    public void release(int id) {
        bookRepository.deleteByOwner(id);
    }
    public void assign(int id,Person selectedPerson) {
        bookRepository.selectOwner(id,selectedPerson);
    }

    public Optional<Book> getTitleVal(String title) {
        return bookRepository.findByTitle(title);
    }
}