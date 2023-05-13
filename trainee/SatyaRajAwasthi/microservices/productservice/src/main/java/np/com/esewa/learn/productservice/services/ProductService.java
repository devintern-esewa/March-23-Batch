package np.com.esewa.learn.productservice.services;

import np.com.esewa.learn.productservice.resources.ProductRequest;
import np.com.esewa.learn.productservice.resources.ProductResponse;

/**
 * @author SatyaRajAwasth1 on 5/8/2023
 * A service interface for product
 */
public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void editProduct(long productId, ProductRequest productRequest);

    public void deleteProductById(long productId);
}
