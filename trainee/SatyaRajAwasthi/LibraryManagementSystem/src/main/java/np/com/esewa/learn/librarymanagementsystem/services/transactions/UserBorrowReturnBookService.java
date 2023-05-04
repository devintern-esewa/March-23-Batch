package np.com.esewa.learn.librarymanagementsystem.services.transactions;

import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.exceptions.UserNotFoundException;

/**
 * @author Thinkpad on 4/26/2023
 * @project LibraryManagementSystem
 */
public interface UserBorrowReturnBookService {
    public boolean borrowBook(long userId, long bookIsbn) throws BooKNotFoundException, UserNotFoundException;
    public boolean returnBook(long userId, long bookIsbn) throws BooKNotFoundException, UserNotFoundException;
}
