package com.my.app.model;
import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "FIO")
    private String FIO;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private int year;

    public Person() {

    }

    public Person(String FIO, int year) {
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

    public void setYear(int age) {
        this.year = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + FIO + '\'' +
                ", age=" + year +
                '}';
    }
}