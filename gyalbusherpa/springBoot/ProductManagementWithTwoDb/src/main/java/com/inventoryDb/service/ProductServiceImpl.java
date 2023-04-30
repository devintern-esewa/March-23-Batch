package com.inventoryDb.service;

import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import com.inventoryDb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ArrayList<Product> convertFilePathToProduct(String filePath){

        ArrayList<Product> products = new ArrayList<>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while((line = bufferedReader.readLine())!=null){
                String[] split = line.split(",");
                if(split[0].equals("name")){
                    continue;
                }

                String name = split[0];
                String code = split[1];
                ProductEnum productEnum = ProductEnum.valueOf(split[2]);
                double quantity = Double.parseDouble(split[3]);
                double price = Double.parseDouble(split[4]);

                Product product =  Product.builder()
                        .name(name)
                        .code(code)
                        .productStatus(productEnum)
                        .quantity(quantity)
                        .price(price)
                        .createdDate(new Date())
                        .lastModifiedDate(new Date())
                        .build();

                products.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product saveProduct(List<Product> products) {
        Product pro = new Product();
        for (Product product: products) {
            productRepository.save(product);
            pro.setProductStatus(ProductEnum.ACTIVE);
            productRepository.save(product);
        }
        return pro;
    }
}
