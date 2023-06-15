package np.com.esewa.orderservice.repositories;

import np.com.esewa.orderservice.documents.OrderedProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/15/2023
 * @project shopping-cart-micro-services
 * A repository for managing mongo document and life cycle of OrdereProduct
 */

@Repository
public interface OrderedProductRepository extends MongoRepository<OrderedProduct, Integer> {
}
