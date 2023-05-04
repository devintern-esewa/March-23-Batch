package com.multipledatabase.inventorydb.services;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.multipledatabase.security.Cipher.*;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public Product createProduct(ProductDto productDto) {

        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setStatus(ProductEnum.valueOf(productDto.getStatus()));
        product.setQty(productDto.getQty());
        product.setPrice(productDto.getPrice());

        return product;
    }

    public ProductDto createProductDto(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setCode(decryption(product.getCode()));
        productDto.setName(product.getName());
        productDto.setStatus(String.valueOf(product.getStatus()));
        productDto.setQty(product.getQty());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProduct() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = new ArrayList<>(productRepository.findAll());

        for (Product product : productList) {

            productDtoList.add(createProductDto(product));

        }
        return productDtoList;


    }

    @Override
    public void addAllProduct(List<Product> productList) {

        for (Product product : productList) {
            logger.info(product.getCode() + " " + "Product code before encryption");
            logger.info("Encrypting the product code to set in database");
            product.setCode(encryption(product.getCode(), generateKey()));
            logger.info("Product code after encryption" + product.getCode());
        }
        logger.info("Saving all the product in database");
        productRepository.saveAll(productList);
        logger.info("Saved all the product in database");
    }


    @Override
    public boolean checkProductStatus(Product product, List<ProductDto> productList) {

        boolean flag = true;

        for (ProductDto productDto : productList) {
            String code = decryption(product.getCode());
            if (code.equals(productDto.getCode()) || product.getStatus().equals(ProductEnum.valueOf(productDto.getStatus()))) {
                flag = false;
                break;
            }
        }
        return flag;


    }
}
