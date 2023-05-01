package com.example.multipledatabaseconnection.productDetails.repo;

import com.example.multipledatabaseconnection.productDetails.enums.ProductStatus;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {
    @Query("select p from ProductDetails p where p.code=:code")
    ProductDetails getByCode(String code);
}
