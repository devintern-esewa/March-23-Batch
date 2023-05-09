package com.multipledatabase.inventorydb.services;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.exception.ProductAlreadyExistException;
import com.multipledatabase.inventorydb.exception.ProductDoesNotExistException;
import com.multipledatabase.inventorydb.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.multipledatabase.security.Cipher.*;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public Product convertToProductEntity(ProductDto productDto) {

        Product product = new Product();
        product.setCode(encryption(productDto.getCode()));
        product.setName(productDto.getName());
        product.setStatus(ProductEnum.valueOf(productDto.getStatus().toUpperCase()));
        product.setQty(productDto.getQty());
        product.setPrice(productDto.getPrice());

        return product;
    }


    public ProductDto convertToProductDto(Optional<Product> product) {

        ProductDto productDto = new ProductDto();
        productDto.setCode(product.get().getCode());
        productDto.setName(product.get().getName());
        productDto.setStatus(String.valueOf(product.get().getStatus()));
        productDto.setQty(product.get().getQty());
        productDto.setPrice(product.get().getPrice());
        return productDto;
    }

    public static Product createProduct(String name, String code, double qty, double price) {

        Product product1 = new Product();
        product1.setName(name);
        product1.setStatus(ProductEnum.ACTIVE);
        product1.setCode(code);
        product1.setQty(qty);
        product1.setPrice(price);

        return product1;
    }

    public ProductDto findByName(String name) {

        if (productRepository.findByName(name).isPresent()) {
            return convertToProductDto(Optional.of(productRepository.findByName(name).get()));
        } else throw new ProductDoesNotExistException("Does not exist");

    }


    @Override
    public List<ProductDto> getAllProduct() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = new ArrayList<>(productRepository.findAll());

        for (Product product : productList) {

            productDtoList.add(convertToProductDto(Optional.ofNullable(product)));

        }
        return productDtoList;
    }

    @Override
    public boolean addAllProduct(List<Product> productList) {


        logger.info("Saving all the product in database");
        productRepository.saveAll(productList);
        return true;
    }

    public boolean addProduct(ProductDto productDto) {

        if (productRepository.findByName(productDto.getName()).isPresent()) {

            throw new ProductAlreadyExistException("Product Exist");
        } else {
            productRepository.save(convertToProductEntity(productDto));


            return true;
        }
    }


    @Override
    public boolean checkProductStatus(Product product, List<ProductDto> productList) {

        boolean flag = true;

        for (ProductDto productDto : productList) {
            if (product.getCode().equals(productDto.getCode()) && product.getStatus().equals(ProductEnum.valueOf(productDto.getStatus().toUpperCase()))) {
                flag = false;
                break;
            }
        }
        return flag;


    }
}
