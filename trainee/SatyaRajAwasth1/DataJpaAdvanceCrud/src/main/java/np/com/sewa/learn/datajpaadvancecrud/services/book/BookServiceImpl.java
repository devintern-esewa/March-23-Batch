package np.com.sewa.learn.datajpaadvancecrud.services.book;

import lombok.extern.slf4j.Slf4j;
import np.com.sewa.learn.datajpaadvancecrud.entities.Book;
import np.com.sewa.learn.datajpaadvancecrud.entities.enums.BookStatus;
import np.com.sewa.learn.datajpaadvancecrud.exceptions.BooKNotFoundException;
import np.com.sewa.learn.datajpaadvancecrud.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addbook(Book book) {

        bookRepository.save(book);
        log.info("Added Title: "+book.getTitle()+" "+" ISBN: "+book.getIsbn());
    }

    @Override
    public boolean editBookDetails(Book editedBook , long isbn) throws BooKNotFoundException {
            Book bookToBeEdited = bookRepository.findById(isbn).orElseThrow(BooKNotFoundException::new);

            bookToBeEdited.setPublisher(editedBook.getPublisher());
            bookToBeEdited.setAuthors(editedBook.getAuthors());
            bookToBeEdited.setTitle(editedBook.getTitle());
            bookToBeEdited.setPublicationYear(editedBook.getPublicationYear());
            bookToBeEdited.setGenres(editedBook.getGenres());
            bookToBeEdited.setBookStatus(editedBook.getBookStatus());

            bookRepository.save(bookToBeEdited);
            log.info("Updated Title: "+bookToBeEdited.getTitle()+" "+" ISBN: "+bookToBeEdited.getIsbn());

            return true;
        }

    @Override
    public boolean deleteBook(long isbn) throws BooKNotFoundException {
        Book bookToBeDeleted = bookRepository.findById(isbn).orElseThrow(BooKNotFoundException::new);
        bookToBeDeleted.setBookStatus(BookStatus.DELETED);

        bookRepository.save(bookToBeDeleted);
        log.info("Deleted Title: "+bookToBeDeleted.getTitle()+" "+" ISBN: "+bookToBeDeleted.getIsbn());

        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        // return books only that are not deleted
        List<Book> booksTobeReturned = bookRepository.findAll().stream().filter(book -> book
                .getBookStatus()!=BookStatus.DELETED).toList();
        log.info(booksTobeReturned.toString());

        return booksTobeReturned;
    }

    @Override
    public Book getBookByIsbn(long isbn) throws BooKNotFoundException {
        log.info(" Getting book with ISBN: "+isbn);
        return bookRepository.findById(isbn).orElseThrow(BooKNotFoundException::new);
    }

}
