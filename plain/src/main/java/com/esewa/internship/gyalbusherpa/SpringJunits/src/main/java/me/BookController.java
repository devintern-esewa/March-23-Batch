package me;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBookRecords(){
        return bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable(value="bookId")Long bookId){
        return bookRepository.findById(bookId).get();
    }

    @PostMapping
    public Book createBookRecord(@RequestBody @Valid Book bookRecord){
        return bookRepository.save(bookRecord);
    }

    @PutMapping
    public Book updateBookRecord(@RequestBody @Valid Book bookRecord) throws NotFoundException{
        if(bookRecord == null || bookRecord.getBookId() == 0){
            throw new NotFoundException("BookRecord or Id must not be null");
        }
        Optional<Book> optionalBook = bookRepository.findById(bookRecord.getBookId());
        if(!optionalBook.isPresent()){
            throw new NotFoundException("Book with ID: " + bookRecord.getBookId() + " does not exist.");
        }

        Book existingBookRecord = optionalBook.get();
        existingBookRecord.setName(bookRecord.getName());
        existingBookRecord.setSummary(bookRecord.getSummary());
        existingBookRecord.setRating(bookRecord.getRating());

        return bookRepository.save(existingBookRecord);
    }

    @DeleteMapping(value = "{bookId}")
    public void deleteBookById(@PathVariable(value = "bookId")Long bookId) throws Exception{
        if(!bookRepository.findById(bookId).isPresent()){
            throw new NotFoundException("bookId" + bookId + "not present");
        }
        bookRepository.deleteById(bookId);
    }


}
