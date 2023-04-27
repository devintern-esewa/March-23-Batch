package com.product.repository;

import com.product.dto.ProductDto;
import com.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByProductName(String productName);

    @Query("select new com.product.dto.ProductDto(product.productName,product.productCode,product.quantity,product.price) from Product product")
    List<ProductDto> getAllProductDto();

    @Query("select new com.product.dto.ProductDto(product.productName,product.productCode,product.quantity,product.price) from Product product where product.id= ?1")
    Optional<ProductDto> getProductDtoById(long id);
}
