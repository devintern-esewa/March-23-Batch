package np.com.esewa.learn.librarymanagementsystem.services.book;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.Publisher;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;

import java.util.List;

public interface BookService {
    public void addbook(Book book);
    public boolean editBookDetails(Book bookToBeEdited, long ISBN) throws BooKNotFoundException;
    public boolean deleteBook(long ISBN) throws BooKNotFoundException;
    public List<Book> getAllBooks(int startingPageNo);
    public Book getBookByIsbn(long ISBN) throws BooKNotFoundException;
    public List<Book> getAllAvailableBooks();
    public List<Book> searchBooksByTitle(String bookTitle);
    public List<Book> searchBooksByAuthor(String authorName);
    public List<Book> searchBooksByGenre(String genreName);
    public List<Book> searchBooksByPublisherName(String publisherName);

}
