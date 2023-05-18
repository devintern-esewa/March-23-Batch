package com.restapi.product.dao;


import com.restapi.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {



   Optional<Product> findByProductName(String productName);


   void deleteByProductName(String productName);

}
