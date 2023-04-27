package np.com.esewa.learn.librarymanagementsystem.repositories;

import np.com.esewa.learn.librarymanagementsystem.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
}
