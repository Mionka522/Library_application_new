package com.my.lib.repositories;

import com.my.lib.model.Book;
import com.my.lib.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Person> findOwnerByBook(int id);

    @Query("UPDATE Book b SET b.owner=NULL WHERE Person.id=:id")
    void deleteByOwner(int id);

    @Query("UPDATE Book b SET b.owner=:id WHERE Person.id=:selectedPerson")
    void selectOwner(int id,Person selectedPerson);

    Optional<Book> findByTitle(String title);

    Optional<Person> findOwner(String title);

}