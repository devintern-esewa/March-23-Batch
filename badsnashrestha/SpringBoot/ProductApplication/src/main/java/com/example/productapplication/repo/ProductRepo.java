package com.example.productapplication.repo;

import com.example.productapplication.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    //a JPQL( Java Persistence query language) is db independent. Here Product is an entity name

    //@Query: to define custom queries to  be executed by the db
    @Query(value = "select s from Product s")
    List<Product> findAllProduct();

    @Transactional
    @Modifying //to mark a method that the query being executed will change the state of db
    @Query(value = "INSERT INTO Product p (p.productName,p.price) values(?1,?2)")
    void saveNewProduct(String productName, double price);

    @Transactional
    @Modifying
    @Query(value = "delete from Product p where p.productId=:productId")
    void deleteProductById(@Param("productId") Integer productId);


    @Query(value = "select s from Product s where s.productId=:productId")
    Optional<Product> findProductById(@Param("productId") Integer integer);

    //native Query
    //dependent on db

    @Transactional
    @Modifying
    @Query(value = "update products_info set product_name=:productName,price=:price where product_id=:productId", nativeQuery = true)
    void updateProduct(@Param("productId") Integer integer, @Param("productName") String productName, @Param("price") double price);


}
