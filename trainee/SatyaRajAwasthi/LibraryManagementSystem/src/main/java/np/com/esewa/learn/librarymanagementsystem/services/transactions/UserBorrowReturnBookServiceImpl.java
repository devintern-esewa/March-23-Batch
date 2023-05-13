package np.com.esewa.learn.librarymanagementsystem.services.transactions;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.TransactionStatus;
import np.com.esewa.learn.librarymanagementsystem.entities.transactions.UserBorrowReturnBook;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.repositories.transactions.UserBorrowReturnBookRepository;
import np.com.esewa.learn.librarymanagementsystem.services.book.BookService;
import np.com.esewa.learn.librarymanagementsystem.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thinkpad on 4/26/2023
 * @project LibraryManagementSystem
 */
@Service
public class UserBorrowReturnBookServiceImpl implements UserBorrowReturnBookService {
    private final UserBorrowReturnBookRepository userBorrowReturnBookRepository;
    public UserBorrowReturnBookServiceImpl(UserBorrowReturnBookRepository userBorrowReturnBookRepository) {
        this.userBorrowReturnBookRepository = userBorrowReturnBookRepository;
    }

    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean borrowBook(long userId, long bookIsbnNo)  {
        Book book;
        User user;
        UserBorrowReturnBook userBorrowingBook = new UserBorrowReturnBook();

            user = userService.getUserById(userId);
            try {
                book = bookService.getBookByIsbn(bookIsbnNo);
            }
            catch (BooKNotFoundException e) {
                return false;
            }
            book.setBookStatus(BookStatus.NOT_AVAILABLE);

            userBorrowingBook.setBook(book);
            userBorrowingBook.setUser(user);
            userBorrowingBook.setTransactionStatus(TransactionStatus.BORROWED);

            userBorrowReturnBookRepository.save(userBorrowingBook);
            return true;
    }

    @Override
    public boolean returnBook(long userId, long bookIsbnNo) {
        Book book;
        User user;
        UserBorrowReturnBook userReturningBook = new UserBorrowReturnBook();

        user = userService.getUserById(userId);
        try {
            book = bookService.getBookByIsbn(bookIsbnNo);
        }
        catch (BooKNotFoundException e) {
            return false;
        }
        // set book status available and save back to book database
        book.setBookStatus(BookStatus.AVAILABLE);
        bookService.addbook(book);

        userReturningBook.setBook(book);
        userReturningBook.setUser(user);
        userReturningBook.setTransactionStatus(TransactionStatus.RETURNED);

        userBorrowReturnBookRepository.save(userReturningBook);
        return true;
    }

}
