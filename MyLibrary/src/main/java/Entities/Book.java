/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author irina
 */
@Entity
public class Book {
    
    @Id
    private long isbn;
    @Basic
    private String title;
    private int year;
    private int copy;
    private int borrowedCopies;
    private int remaininCopies;
    private boolean register;
    
    @ManyToOne
    private Author author;
    
    @ManyToOne
    private Editorial editorial;

    public Book() {
        this.register = true;
    }

    public Book(long isbn, String title, int year, int copy, int borrowedCopies, int remaininCopies, Author author, Editorial editorial) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.copy = copy;
        this.borrowedCopies = borrowedCopies;
        this.remaininCopies = remaininCopies;
        this.register = true;
        this.author = author;
        this.editorial = editorial;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public int getRemaininCopies() {
        return remaininCopies;
    }

    public void setRemaininCopies(int remaininCopies) {
        this.remaininCopies = remaininCopies;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    
    
    
}
