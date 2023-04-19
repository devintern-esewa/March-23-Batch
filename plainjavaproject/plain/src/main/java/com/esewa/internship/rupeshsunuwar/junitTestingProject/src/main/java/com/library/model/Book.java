package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.library.model;

public class Book {

    private int bookId;
    private String bookName;
    private String author;

    boolean isIssued;

    public Book(int bookId,String bookName, String author, boolean isIssued) {
        this.bookId=bookId;
        this.bookName = bookName;
        this.author = author;
        this.isIssued = isIssued;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }
}
