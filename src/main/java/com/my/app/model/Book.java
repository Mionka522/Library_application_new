package com.my.app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private int year;
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;
    public Book(){

    }

    public Book(String title, String author, int year, int id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
