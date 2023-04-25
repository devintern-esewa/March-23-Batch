package np.com.esewa.learn.librarymanagementsystem.controllers;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.services.book.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam int startingPageNo){
        return bookService.getAllBooks( startingPageNo );
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
         bookService.addbook(book);
    }



}
