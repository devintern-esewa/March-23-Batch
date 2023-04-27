package np.com.esewa.learn.librarymanagementsystem.services.user;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.Role;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.exceptions.UserNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.repositories.UserRepository;
import np.com.esewa.learn.librarymanagementsystem.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getAllBooks(int startingPageNo) {
        return bookService.getAllBooks(startingPageNo);
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return bookService.getAllAvailableBooks();
    }


    @Override
    public List<Book> searchBooksByTitle(String bookTitle) {
        return bookService.searchBooksByTitle(bookTitle);
    }

    @Override
    public List<Book> searchBooksByAuthorName(String authorName) {
        return bookService.searchBooksByAuthor(authorName);
    }

    @Override
    public List<Book> searchBooksByGenreName(String genreName) {
        return bookService.searchBooksByGenre(genreName);
    }

    @Override
    public List<Book> searchBooksByPublisherName(String publisherName) {
        return bookService.searchBooksByPublisherName(publisherName);
    }

    @Override
    public boolean borrowBook(long userId, long bookIsbnNo) throws BooKNotFoundException, UserNotFoundException {
        Book book = null;
        User user = null;
        try {

            user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            book = bookService.getBookByIsbn(bookIsbnNo);
            book.setBookStatus(BookStatus.BORROWED);
            List<Book> listOfBorrowedBooks = new ArrayList<>();
            listOfBorrowedBooks.add(book);
            user.setListOfBorrowedBooks(listOfBorrowedBooks);
            userRepository.save(user);

            return true;
        }
        catch (BooKNotFoundException e) {
            throw new BooKNotFoundException();
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public boolean returnBook(long userId, long bookIsbnNo) throws BooKNotFoundException, UserNotFoundException {
        Book book = null;
        User user = null;
        try {

            user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            book = bookService.getBookByIsbn(bookIsbnNo);

            book.setBookStatus(BookStatus.AVAILABLE);
            List<Book> listOfBorrowedBooks = user.getListOfBorrowedBooks();
            listOfBorrowedBooks.remove(book);
            user.setListOfBorrowedBooks(listOfBorrowedBooks);
            userRepository.save(user);
            bookService.addbook(book);

            return true;
        }
        catch (BooKNotFoundException e) {
            throw new BooKNotFoundException();
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void trackBrowsingHistory() {

    }

    @Override
    public boolean addUser(User user) {
        user.setRole(Role.MEMBER);
        userRepository.save(user);
        return true;
    }


}
