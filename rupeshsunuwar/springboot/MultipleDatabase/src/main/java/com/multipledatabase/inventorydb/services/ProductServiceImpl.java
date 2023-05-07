package com.multipledatabase.inventorydb.services;

import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


    public Product convertToProductEntity(ProductDto productDto) {

        Product product = new Product();
        product.setCode(encryption(productDto.getCode()));
        product.setName(productDto.getName());
        product.setStatus(ProductEnum.valueOf(productDto.getStatus().toUpperCase()));
        product.setQty(productDto.getQty());
        product.setPrice(productDto.getPrice());

        return product;
    }


    public ProductDto convertToProductDto(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setStatus(String.valueOf(product.getStatus()));
        productDto.setQty(product.getQty());
        productDto.setPrice(product.getPrice());
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

        Product product = productRepository.findByName(name);
        System.out.println(product.getName());
        return convertToProductDto(productRepository.findByName(name));


    }


    @Override
    public List<ProductDto> getAllProduct() {

        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = new ArrayList<>(productRepository.findAll());

        for (Product product : productList) {

            productDtoList.add(convertToProductDto(product));

        }
        return productDtoList;
    }

    @Override
    public void addAllProduct(List<Product> productList) {


        logger.info("Saving all the product in database");
        productRepository.saveAll(productList);
        logger.info("Saved all the product in database");
    }

    public ProductDto addProduct(ProductDto productDto) {

        Product product = productRepository.save(convertToProductEntity(productDto));

        return convertToProductDto(product);
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
