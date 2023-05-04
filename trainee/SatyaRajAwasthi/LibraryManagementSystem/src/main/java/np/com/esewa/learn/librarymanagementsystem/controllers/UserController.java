package np.com.esewa.learn.librarymanagementsystem.controllers;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.entities.transactions.UserBorrowReturnBook;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.exceptions.UserNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.services.transactions.UserBorrowReturnBookService;
import np.com.esewa.learn.librarymanagementsystem.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thinkpad on 4/25/2023
 * @project LibraryManagementSystem
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private UserBorrowReturnBookService userBorrowReturnBookService;
    @Autowired
    public void setUserBorrowReturnBookService(UserBorrowReturnBookService userBorrowReturnBookService) {
        this.userBorrowReturnBookService = userBorrowReturnBookService;
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
        return userBorrowReturnBookService.borrowBook(usrId,bookIsbnNo);
    }
    @GetMapping("/{userId}/return/{bookIsbnNo}")
    public boolean returnBook(@RequestParam long usrId, long bookIsbnNo) throws UserNotFoundException, BooKNotFoundException {
        return userBorrowReturnBookService.returnBook(usrId,bookIsbnNo);
    }
}
