package com.my.app.services;

import com.my.app.model.Book;
import com.my.app.model.Person;
import com.my.app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {

        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }
    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(book -> {book.setOwner(null);});
    }
    @Transactional
    public void assign(int id,Person selectedPerson) {
        bookRepository.findById(id).ifPresent(book -> {book.setOwner(selectedPerson);});
    }

    public Optional<Book> getTitleVal(String title) {
        return bookRepository.findByTitle(title);
    }
    public List <Book> findBookByOwnerId(int id){
        return bookRepository.findBookByOwnerId(id);
    }
}