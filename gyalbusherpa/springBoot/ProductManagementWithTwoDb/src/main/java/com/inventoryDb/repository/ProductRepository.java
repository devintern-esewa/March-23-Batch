package com.inventoryDb.repository;

import com.inventoryDb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select code from products",nativeQuery = true)
    List<String> getAllCode();
}
