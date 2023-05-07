package np.com.esewa.learn.librarymanagementsystem.services.user;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User getUserById(long userId);
    public List<Book> getAllBooks(int startingPageNo);
    public List<Book> getAllAvailableBooks();
    public List<Book> searchBooksByTitle(String bookTitle);
    public List<Book> searchBooksByAuthorName(String authorName);
    public List<Book> searchBooksByGenreName(String genreName);
    public List<Book> searchBooksByPublisherName(String publisherName);
    public void trackBrowsingHistory();
    public boolean addUser(User user);

}
