package np.com.esewa.learn.librarymanagementsystem.services.user;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.repositories.UserRepository;
import np.com.esewa.learn.librarymanagementsystem.services.book.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private BookService bookService;
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getAllBooks(int startingPageNo) {
        return bookService.getAllBooks(startingPageNo);
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return null;
    }


    @Override
    public Book searchBooksByTitle(String bookTitle) {
        return null;
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Book> searchBooksByGenre(String genreName) {
        return null;
    }

    @Override
    public List<Book> searchBooksByPublisher(String publisherName) {
        return null;
    }

    @Override
    public boolean borrowBook(Book book) {
        return false;
    }

    @Override
    public boolean returnBook(Book book) {
        return false;
    }

    @Override
    public void trackBrowsingHistory() {

    }


}
