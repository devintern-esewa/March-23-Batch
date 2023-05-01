package com.inventoryDb.service;

import com.configDb.model.FileDetail;
import com.configDb.repository.FileDetailRepository;
import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.model.Product;
import com.inventoryDb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileDetailRepository fileDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, FileDetailRepository fileDetailRepository) {
        this.productRepository = productRepository;
        this.fileDetailRepository = fileDetailRepository;
    }

    @Override
    public List<Product> convertCsvDataInFilePathToProduct(String filePath) {

        List<Product> products = new ArrayList<>();

        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                if (split[0].equals("name")) {
                    continue;
                }

                String name = split[0];
                String code = split[1];
                double quantity = Double.parseDouble(split[3]);
                double price = Double.parseDouble(split[4]);

                Product product = Product.builder()
                        .name(name)
                        .code(code)
                        .productStatus(ProductEnum.ACTIVE)
                        .quantity(quantity)
                        .price(price)
                        .build();
                products.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public List<Product> processProduct(List<Product> products, String filePath) {

        List<Product> product = new ArrayList<>();
        HashSet<String> codeIds = new HashSet<>();

        int successCount = 0;
        int failCount = 0;

        FileDetail fileDetail;

        for (Product processingProduct : products) {
            Optional<Product> byCodeAndStatus = productRepository.getProductByProductStatusAndCode(ProductEnum.ACTIVE,
                    processingProduct.getCode());

            boolean flag = byCodeAndStatus.isPresent();

            if (codeIds.contains(processingProduct.getCode()) ||
                    flag && byCodeAndStatus.get().getCode().equals(processingProduct.getCode())) {
                fileDetail = fileDetailRepository.findFileDetailByFilePath(filePath);
                failCount++;

                fileDetail.setFailureCount(failCount);
                fileDetailRepository.save(fileDetail);
            } else {
                product.add(processingProduct);
                codeIds.add(processingProduct.getCode());
                successCount++;

                fileDetail = fileDetailRepository.findFileDetailByFilePath(filePath);
                fileDetail.setSuccessCount(successCount);
                fileDetailRepository.save(fileDetail);
            }
        }
        return product;
    }

    @Override
    public void saveProduct(List<Product> products) {
        productRepository.saveAll(products);
    }
}