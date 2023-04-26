package np.com.esewa.learn.librarymanagementsystem.services.user;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;

import java.util.List;

public interface UserService {
    public List<Book> getAllBooks(int startingPageNo);
    public List<Book> getAllAvailableBooks();
    public Book searchBooksByTitle(String bookTitle);
    public List<Book> searchBooksByAuthor(String authorName);
    public List<Book> searchBooksByGenre(String genreName);
    public List<Book> searchBooksByPublisher(String publisherName);
    public boolean borrowBook(Book book);
    public boolean returnBook(Book book);
    public void trackBrowsingHistory();

}
