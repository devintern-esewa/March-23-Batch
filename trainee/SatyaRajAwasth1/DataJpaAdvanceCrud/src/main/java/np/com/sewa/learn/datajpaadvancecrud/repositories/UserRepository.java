package np.com.sewa.learn.datajpaadvancecrud.repositories;

import np.com.sewa.learn.datajpaadvancecrud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
