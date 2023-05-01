package com.inventoryDb.repository;

import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductByProductStatusAndCode(ProductEnum productEnum, String code);
}
