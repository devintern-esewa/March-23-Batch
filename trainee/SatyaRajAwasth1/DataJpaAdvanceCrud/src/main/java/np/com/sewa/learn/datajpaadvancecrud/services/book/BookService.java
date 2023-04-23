package np.com.sewa.learn.datajpaadvancecrud.services.book;

import np.com.sewa.learn.datajpaadvancecrud.entities.Book;
import np.com.sewa.learn.datajpaadvancecrud.exceptions.BooKNotFoundException;

import java.util.List;

public interface BookService {
    public void addbook(Book book);
    public boolean editBookDetails(Book bookToBeEdited, long ISBN) throws BooKNotFoundException;
    public boolean deleteBook(long ISBN);
    public List<Book> getAllBooks();
    public Book getBookByIsbn(long ISBN);

}
