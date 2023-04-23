package np.com.sewa.learn.datajpaadvancecrud.repositories;

import np.com.sewa.learn.datajpaadvancecrud.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByISBN(long isbn);
}
