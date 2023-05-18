package com.example.mulltipledbconnectiontask.inventory.repo;

import com.example.mulltipledbconnectiontask.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
