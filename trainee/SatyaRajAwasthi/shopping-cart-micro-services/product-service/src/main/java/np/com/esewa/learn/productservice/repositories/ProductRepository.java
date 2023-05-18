package np.com.esewa.learn.productservice.repositories;

import np.com.esewa.learn.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thinkpad on 5/8/2023
 * A JPA repository for Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
