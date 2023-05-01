package com.inventoryDb.repository;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductByProductStatusAndCode(ProductEnum productEnum, String code);

    @Query("select new com.inventoryDb.dto.ProductDto(products.name,products.code,products.quantity,products.price) " +
            "from Product products WHERE products.productStatus = 'ACTIVE'")
    List<ProductDto> getAllProductDto();

}
