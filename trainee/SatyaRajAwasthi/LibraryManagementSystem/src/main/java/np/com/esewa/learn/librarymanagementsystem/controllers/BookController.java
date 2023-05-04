package np.com.esewa.learn.librarymanagementsystem.controllers;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam int startingPageNo){
        List<Book> books = bookService.getAllBooks( startingPageNo );
        if (books == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"No records found");
        }
        return books;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
         bookService.addbook(book);
    }



}
