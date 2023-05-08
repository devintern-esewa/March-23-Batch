package com.inventoryDb.service;

import com.configDb.model.FileDetail;
import com.configDb.repository.FileDetailRepository;
import com.inventoryDb.annotation.DecryptProduct;
import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.enums.ProductEnum;
import com.inventoryDb.exception.ResourceNotFoundException;
import com.inventoryDb.model.Product;
import com.inventoryDb.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @CacheEvict(value = "saveProduct", key = "#root.methodName")
    public void saveProduct(List<Product> products) {
        productRepository.saveAll(products);
    }


    @Override
    @DecryptProduct
    @Cacheable(value = "getAllProduct", key= "#root.methodName")
    public List<ProductDto> getAllProducts() {
        return productRepository.getAllProductDto();
    }

    @Override
    @DecryptProduct
    @Cacheable(value = "getProductByPage", key = "#root.methodName")
    public List<ProductDto> getAllProductsByPage(int offSet, int pageSize, String field) {
        Page<Product> allProducts =
                productRepository.findAll(PageRequest.of(offSet, pageSize).withSort(Sort.Direction.ASC,
                        field));

        List<ProductDto> productsDto = new ArrayList<>();

        for (Product products : allProducts) {
            productsDto.add(
                    ProductDto.builder()
                            .name(products.getName())
                            .code(products.getCode())
                            .quantity(products.getQuantity())
                            .price(products.getPrice())
                            .build()
            );
        }
        return productsDto;
    }

    @Override
    @DecryptProduct
    @Cacheable(value = "productById",key = "#id")
    public List<ProductDto> getProductById(long id) {
        Product products = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product is" +
                " not found"));

        List<ProductDto> productsDto = new ArrayList<>();

        ProductDto productDto = new ProductDto();
        productDto.setName(products.getName());
        productDto.setCode(products.getCode());
        productDto.setQuantity(products.getQuantity());
        productDto.setPrice(products.getPrice());

        productsDto.add(productDto);

        return productsDto;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " +
                "with id: " + id + " doesn't exist"));
        product.setProductStatus(ProductEnum.DELETED);
        productRepository.save(product);
    }
}
