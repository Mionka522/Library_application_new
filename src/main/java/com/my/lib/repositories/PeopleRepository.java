package com.my.lib.repositories;

import com.my.lib.model.Book;
import com.my.lib.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

@Query("SELECT b FROM Book b WHERE Book.owner=:id")
    List<Book> findBookById(int id);

    Optional<Person> findByFIO(String FIO);
}