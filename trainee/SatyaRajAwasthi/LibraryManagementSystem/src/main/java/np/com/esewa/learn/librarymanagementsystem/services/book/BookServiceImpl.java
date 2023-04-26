package np.com.esewa.learn.librarymanagementsystem.services.book;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;
import np.com.esewa.learn.librarymanagementsystem.exceptions.BooKNotFoundException;
import np.com.esewa.learn.librarymanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        book.setBookStatus(BookStatus.AVAILABLE);
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
    public List<Book> getAllBooks(int startingPageNo) {
        // Get only 10 books from the starting page number
        PageRequest pageRequest = PageRequest.of(startingPageNo,10);
        // return books only that are not deleted
        List<Book> booksTobeReturned = bookRepository.findAll(pageRequest).stream().filter(book -> book
                .getBookStatus()!=BookStatus.DELETED).toList();
        log.info(booksTobeReturned.toString());
        if (booksTobeReturned == null)
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"No records found");
        return booksTobeReturned;
    }

    @Override
    public Book getBookByIsbn(long isbn) throws BooKNotFoundException {
        log.info(" Getting book with ISBN: "+isbn);
        return bookRepository.findById(isbn).orElseThrow(BooKNotFoundException::new);
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return bookRepository.findAllByBookStatus(BookStatus.AVAILABLE);
    }

    @Override
    public List<Book> searchBooksByTitle(String bookTitle) {
        return bookRepository.searchBooksByTitle(bookTitle);
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
        return bookRepository.searchBooksByAuthorName(authorName);
    }

    @Override
    public List<Book> searchBooksByGenre(String genreName) {
        return bookRepository.searchBooksByGenreName(genreName);
    }

    @Override
    public List<Book> searchBooksByPublisherName(String publisherName) {
        return bookRepository.searchBooksByPublisherName(publisherName);
    }

}
