package com.example.multipledatabaseconnection.productDetails.repo;

import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails,Long> {
}
