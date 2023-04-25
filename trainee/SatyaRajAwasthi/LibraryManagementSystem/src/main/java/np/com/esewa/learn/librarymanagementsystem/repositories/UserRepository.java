package np.com.esewa.learn.librarymanagementsystem.repositories;

import np.com.esewa.learn.librarymanagementsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
