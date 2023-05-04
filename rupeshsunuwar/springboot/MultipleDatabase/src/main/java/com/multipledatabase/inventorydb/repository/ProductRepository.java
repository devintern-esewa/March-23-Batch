package com.multipledatabase.inventorydb.repository;

import com.multipledatabase.inventorydb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
