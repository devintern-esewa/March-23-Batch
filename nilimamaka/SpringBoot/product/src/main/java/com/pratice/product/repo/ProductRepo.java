package com.pratice.product.repo;

import com.pratice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    //    @Query("SELECT p FROM Product p WHERE p.name = :name")
    @Query("SELECT p FROM Product p WHERE p.name=?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT a FROM Product a WHERE a.status=?1")
    public List<Product> findAllByActiveProduct(String status);

//    @Query("SELECT p FROM Product p WHERE lower(p.name) = lower(:name)")
//    Optional<Product> findProductByName(@Param("name") String name);

}
