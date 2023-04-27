package np.com.esewa.learn.librarymanagementsystem.controllers;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.exceptions.UserNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thinkpad on 4/25/2023
 * @project LibraryManagementSystem
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/available-books")
    public List<Book> getAllAvailableBooks(){
        return userService.getAllAvailableBooks();
    }

    @GetMapping("/all-books-admin/{startingPageNo}")
    public List<Book> getAllBooks(@RequestParam int startingPageNo){
        return userService.getAllBooks(startingPageNo);
    }

    @GetMapping("/books-by/{authorName}")
    public List<Book> getAllBooksByAuthor(@RequestParam String authorName){
        return userService.searchBooksByAuthorName(authorName);
    }

    @GetMapping("/{userId}/borrow/{bookIsbnNo}")
    public boolean  borrowBook(@RequestParam long usrId, long bookIsbnNo) throws UserNotFoundException, BooKNotFoundException {
        return userService.borrowBook(usrId,bookIsbnNo);
    }
}
