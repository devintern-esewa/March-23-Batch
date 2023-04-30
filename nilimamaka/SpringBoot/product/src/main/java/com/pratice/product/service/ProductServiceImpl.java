package com.pratice.product.service;

import com.pratice.product.customexception.BusinessException;
import com.pratice.product.enums.ProductEnum;
import com.pratice.product.model.Product;
import com.pratice.product.repo.ProductRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    public final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product postProduct(Product product) {
        Optional<Product> productOptional = productRepo.findProductByName(product.getName());
        if (productOptional.isPresent()) {
            throw new IllegalStateException("This product is already in use");
        }
        if(product.getName().isEmpty() || product.getName().length()==0){
            throw new BusinessException("error");
        }
        product.setStatus(ProductEnum.ACTIVE);
        return productRepo.save(product);
    }

    @Override
    public Product putProduct(Product product, Long id) {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setName(product.getName());
        p.setQuantity(product.getQuantity());
        return productRepo.save(p);
    }

    @Override
    public String deleteProduct(Long id) {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setStatus(ProductEnum.DELETE);
        //productRepo.delete(p);
        productRepo.save(p);
        return "the recent record has been deleted";
    }



}
