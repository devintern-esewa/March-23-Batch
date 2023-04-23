package np.com.sewa.learn.datajpaadvancecrud.services.book;

import np.com.sewa.learn.datajpaadvancecrud.entities.Book;
import np.com.sewa.learn.datajpaadvancecrud.exceptions.BooKNotFoundException;
import np.com.sewa.learn.datajpaadvancecrud.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addbook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public boolean editBookDetails(Book bookToBeEdited , long ISBN) throws BooKNotFoundException {
        Optional<Book> book = bookRepository.findById(ISBN);
        Book existingBook = new Book();
        if (book.isPresent()) {
            existingBook = book.get();

            existingBook.setPublisher(bookToBeEdited.getPublisher());
            existingBook.setAuthors(bookToBeEdited.getAuthors());
            existingBook.setTitle(bookToBeEdited.getTitle());
            existingBook.setPublicationYear(bookToBeEdited.getPublicationYear());
            existingBook.setGenres(bookToBeEdited.getGenres());
            bookRepository.save(existingBook);

            return true;
        }
        throw new BooKNotFoundException();
    }

    @Override
    public boolean deleteBook(long ISBN) {
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByIsbn(long isbn) {
        return bookRepository.findBookByISBN(isbn);
    }

}
