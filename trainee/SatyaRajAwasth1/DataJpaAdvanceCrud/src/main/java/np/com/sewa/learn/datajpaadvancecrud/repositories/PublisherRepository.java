package np.com.sewa.learn.datajpaadvancecrud.repositories;

import np.com.sewa.learn.datajpaadvancecrud.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
}
