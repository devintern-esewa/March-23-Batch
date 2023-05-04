package np.com.esewa.learn.librarymanagementsystem.repositories;

import np.com.esewa.learn.librarymanagementsystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
