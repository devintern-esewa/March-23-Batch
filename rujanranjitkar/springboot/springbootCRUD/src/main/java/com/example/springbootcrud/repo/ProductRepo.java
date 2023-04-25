package com.example.springbootcrud.repo;

import com.example.springbootcrud.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "select * from products", nativeQuery = true)
    List<Product> findAllProducts();

    //In native query, table name should be name of database's table name and column name of database
    @Query(value = "select * from products where product_id=?1", nativeQuery = true)
    Optional<Product> findProductById(Long productId);

    //In JPQl, table name should be entity name(Product) and column name should be variable name
    @Transactional
    @Modifying
    @Query(value = "insert into Product p (p.productName,p.price) values (?1,?2)")
    void addNewProduct(String productName, double price);

    @Transactional
    @Modifying
    @Query(value = "update Product p set p.productName=?2, p.price=?3 where p.productId=?1")
    void updateProduct(Long productId, String productName, double price);
    @Transactional
    @Modifying
    @Query(value = "delete from products where product_id=?1", nativeQuery = true)
    void deleteProductById(Long productId);
}
