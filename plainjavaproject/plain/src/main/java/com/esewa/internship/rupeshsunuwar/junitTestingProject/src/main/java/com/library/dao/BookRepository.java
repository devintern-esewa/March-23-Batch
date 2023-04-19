package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.library.dao;

import com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.library.model.Book;

import java.util.List;

public interface BookRepository {

    public Book get(int bookId);

    public boolean create(Book book);

    public boolean update(int id);

    public boolean delete(int bookId);

    public List<Book> getAll();
}
