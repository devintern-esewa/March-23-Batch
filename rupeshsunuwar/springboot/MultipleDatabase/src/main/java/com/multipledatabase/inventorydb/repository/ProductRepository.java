package com.multipledatabase.inventorydb.repository;

import com.multipledatabase.inventorydb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Optional<Product> findByName(String name);
}
