package np.com.esewa.learn.productservice.services;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.productservice.entities.Product;
import np.com.esewa.learn.productservice.exceptions.ProductNotFoundException;
import np.com.esewa.learn.productservice.repositories.ProductRepository;
import np.com.esewa.learn.productservice.resources.ProductRequest;
import np.com.esewa.learn.productservice.resources.ProductResponse;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Thinkpad on 5/9/2023
 * A service implementation for product service
 */

@Service
public class ProductServiceImpl implements ProductService{

    Logger log = Logger.getLogger("ProductServiceImpl.class");
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product
                = Product.builder()
                    .productName(productRequest.getName())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .build();
        product = productRepository.save(product);
        log.info("ProductServiceImpl | addProduct | Saved product with id: "+product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("ProductServiceImpl | getProductById | Getting product for id: "+productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException(" Product with given Id not found")
                );

        return ProductResponse.builder()
        .productName(product.getProductName())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .productId(productId)
        .build();
    }

    @Override
    public void editProduct(long productId, ProductRequest productRequest) {
        log.info("ProductService | editProduct(id, productToBe) | Edit product for id: "+productId);

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
           Product productToBeEdited
                    = Product.builder()
                        .productId(productId)
                        .productName(productRequest.getName())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                    .build();
            productRepository.save(productToBeEdited);
            log.info(" updated product with id: "+productId);
        }
        else {
            log.info(" product with id: "+productId);
            throw new ProductNotFoundException("Product Not found with id "+productId+" To update");
        }


    }

    @Override
    public void deleteProductById(long productId) {
        log.info("ProductService | deleteProductById(id) | delete product for id: "+productId);

        Product productToBeDeleted
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException(" Product with given Id not found")
                );
        productRepository.delete(productToBeDeleted);
        log.info(" deleted product with id: "+productId);
    }
}
