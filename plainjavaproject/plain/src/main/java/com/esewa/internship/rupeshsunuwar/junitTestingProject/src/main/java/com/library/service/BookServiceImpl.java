package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.library.service;

import com.library.dao.BookRepository;
import com.library.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {

        this.bookRepository = bookRepository;


    }


    @Override
    public Book findBookById(int bookId) {
        if (bookId > 0 && bookId <= 100) {
            Book book = bookRepository.get(bookId);
            return book;
        } else if (bookId > 100) {

            System.out.println("Id does not exist");
            return null;
        } else {
            throw new IllegalArgumentException();

        }


    }

    @Override
    public boolean addBook(Book book) {
        System.out.println(book.toString());
        if (book.getAuthor().equals("") || book.getBookName().equals("") || book.getBookId() <= 0) {

            throw new IllegalArgumentException("Invalid Input on the book");
        }
        boolean count = bookRepository.create(book);

        return count;
    }

    @Override
    public boolean delBookById(int bookId) {
        Book book = bookRepository.get(bookId);
        if (book != null) {
            return bookRepository.delete(bookId);
        } else if (bookId <= 0) {
            throw new IllegalArgumentException("Invalid Input");
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBookById(int bookId) {
        Book book = bookRepository.get(bookId);

        if (book != null) {
            book.setBookName("XYZ");
            bookRepository.update(bookId);
            return true;
        } else if (bookId > 100) {
            System.out.println("Id does not exist");
            return false;
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    public List<Book> getAll() {

        return bookRepository.getAll();
    }
}
