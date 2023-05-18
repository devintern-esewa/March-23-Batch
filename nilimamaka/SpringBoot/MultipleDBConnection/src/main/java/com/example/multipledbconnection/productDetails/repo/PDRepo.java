package com.example.multipledbconnection.productDetails.repo;

import com.example.multipledbconnection.productDetails.modelProd.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PDRepo extends JpaRepository<ProductDetails, Long> {
}
