package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import com.library.dao.BookRepository;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

public class BookServiceTest {


    @Mock
    static BookRepository bookRepository;

    @InjectMocks
    static BookServiceImpl bookService;


    public BookServiceTest() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindBookByIdWithValidId() {
        Book book = new Book(555, "Python", "TYo", false);
        Mockito.when(bookRepository.get(anyInt())).thenReturn(book);
        assertEquals(book, bookService.findBookById(5));
    }

    @Test
    public void testFindBookByIdWithInvalidId() {

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.findBookById(-1);

        });

    }

    @Test
    public void testFindBookByIdWithWhenIdDoesNotExist() {

        assertEquals(null, bookService.findBookById(800));

    }


    @Test
    public void testAddBookWhenValidInput() {
        Book b1 = new Book(7, "Psychology", "Rupesh", false);

        Mockito.when(bookRepository.create(b1)).thenReturn(true);

        assertTrue(bookService.addBook(b1));
    }

    @Test
    public void testAddBookWhenInvalidInput() {
        Book b1 = new Book(7, "", "rupesh", true);


        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(b1));

    }

    @Test
    public void testDelBookWhenIdExist() {
        Book book = new Book(5, "Training", "X-man", true);

        Mockito.when(bookRepository.get(anyInt())).thenReturn(book);
        Mockito.when(bookRepository.delete(anyInt())).thenReturn(true);
        assertTrue(bookService.delBookById(5));

    }

    @Test
    public void testDelBookWhenIdDoesNotExist() {
        Mockito.when(bookRepository.get(anyInt())).thenReturn(null);
        assertFalse(bookService.delBookById(100));
    }

    @Test
    public void testDelBookWhenInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> bookService.delBookById(-1));
    }


    @Test
    public void testUpdateBookByIdWhenIdExist() {

        Book book = new Book(5, "Training", "X-man", true);

        Mockito.when(bookRepository.get(anyInt())).thenReturn(book);
        Mockito.when(bookRepository.update(anyInt())).thenReturn(true);
        assertTrue(bookService.updateBookById(5));

    }

    @Test
    public void testUpdateBookWhenIdDoesNotExist() {
        Mockito.when(bookRepository.get(anyInt())).thenReturn(null);
        assertFalse(bookService.delBookById(100));
    }

    @Test
    public void testGetAllBook() {

        List<Book> list = new ArrayList<>();

        Mockito.when(bookRepository.getAll()).thenReturn(list);
        assertEquals(list, bookService.getAll());
    }


}
