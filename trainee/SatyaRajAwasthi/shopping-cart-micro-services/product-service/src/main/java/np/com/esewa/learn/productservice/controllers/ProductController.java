package np.com.esewa.learn.productservice.controllers;

import np.com.esewa.learn.productservice.resources.ProductRequest;
import np.com.esewa.learn.productservice.resources.ProductResponse;
import np.com.esewa.learn.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1 on 5/8/2023
 *  Controller class for products mapping and requests.
 */

@RestController
@RequestMapping("/api/product")
public class ProductController {
    Logger log = Logger.getLogger("ProductController.class");
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        log.info("ProductController | addProduct | request with "+productRequest.toString());
        long productId = productService.addProduct(productRequest);
        log.info("added products productId: "+productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        log.info("ProductController | getProductById | request with "+productId);
        ProductResponse productResponse = productService.getProductById(productId);
        log.info("get product response with details: "+productResponse.toString());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> editProduct(@PathVariable("id") long productId, @RequestBody ProductRequest productRequest){
        log.info("ProductController | editProduct | request with "+productId + " "+productRequest.toString());
        productService.editProduct(productId, productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") long productId){
        log.info("ProductController | deleteProductById | request with "+productId );
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
