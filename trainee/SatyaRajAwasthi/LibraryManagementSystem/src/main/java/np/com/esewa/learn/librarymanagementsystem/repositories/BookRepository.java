package np.com.esewa.learn.librarymanagementsystem.repositories;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b WHERE b.bookStatus LIKE %?1%", nativeQuery = true)
    List<Book> findAllByBookStatus(BookStatus bookStatus);
    @Query(value = "SELECT * FROM Book b WHERE EXISTS (SELECT 1 FROM unnest(b.authors) a WHERE a.name = :authorName)", nativeQuery = true)
    List<Book> searchBooksByAuthor(String authorName);
    @Query
    List<Book> searchBooksByTitle(String title);
    @Query
    List<Book> searchBooksByPublisher(String publisherName);
    @Query
    List<Book> searchBooksByGenre(String genreName);

}
