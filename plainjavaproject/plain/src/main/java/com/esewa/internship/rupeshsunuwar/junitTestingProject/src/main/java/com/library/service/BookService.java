package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.library.service;

import com.library.model.Book;

import java.util.List;

public interface BookService {


    public Book findBookById(int bookId);

    public boolean addBook(Book book);

    public boolean delBookById(int bookId);

    public boolean updateBookById(int bookId);

    public List<Book> getAll();

}
