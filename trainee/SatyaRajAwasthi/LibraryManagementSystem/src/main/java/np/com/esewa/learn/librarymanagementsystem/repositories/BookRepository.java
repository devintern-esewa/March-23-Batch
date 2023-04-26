package np.com.esewa.learn.librarymanagementsystem.repositories;

import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query(value = "SELECT * FROM books b WHERE b.status LIKE %?1%", nativeQuery = true)
//    List<Book> findAllByBookStatus(BookStatus status);


    List<Book> findAllByBookStatus(BookStatus bookStatus);
    @Query(value = "SELECT * FROM Book b WHERE EXISTS (SELECT 1 FROM unnest(b.authors) a WHERE a.name = :authorName)", nativeQuery = true)
    List<Book> searchBooksByAuthorName(String authorName);
    List<Book> searchBooksByTitle(String title);
    @Query(value = "SELECT * FROM Book b WHERE EXISTS (SELECT 1 FROM unnest(b.publisher) p WHERE p.name = :publisherName)", nativeQuery = true)
    List<Book> searchBooksByPublisherName(String publisherName);
    @Query(value = "SELECT * FROM Book b WHERE EXISTS (SELECT 1 FROM unnest(b.genres) g WHERE g.name = :genreName)", nativeQuery = true)
    List<Book> searchBooksByGenreName(String genreName);

}
