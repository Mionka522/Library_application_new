package com.my.lib.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Поле не должно быть пустое")
    @Size(min = 2, max = 30, message = "ФИО не может быть меньше 2 и более 30 символов")
    @Column(name = "FIO")
    private String FIO;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private int year;
@OneToMany
private List<Book> books;

    public Person() {

    }

    public Person(int id, String FIO, int year) {
        this.id = id;
        this.FIO = FIO;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
