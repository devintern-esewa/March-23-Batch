package com.inventoryDb.aop;

import com.inventoryDb.dto.ProductDto;
import com.inventoryDb.model.Product;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class AspectConfig {
    private final RSAEncryptionService rsaEncryptionService;

    public AspectConfig(RSAEncryptionService rsaEncryptionService) {
        this.rsaEncryptionService = rsaEncryptionService;
    }

    @Before("execution(* com.inventoryDb.service.ProductServiceImpl.processProduct(..)) && args(products,filePath)")
    public void encryptProductName(List<Product> products, String filePath) throws Exception {
        for (Product product : products) {
            String encryptedName = rsaEncryptionService.encrypt(product.getName());
            product.setName(encryptedName);
        }
    }

    @Around("execution(* com.inventoryDb.service.ProductServiceImpl.getAllProducts())")
    public Object decryptProductName(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        List<ProductDto> products = (List<ProductDto>) proceedingJoinPoint.proceed();

        for (ProductDto product : products) {
            String decryptedName = rsaEncryptionService.decryptProductName(product.getName());
            product.setName(decryptedName);
        }

        return products;
    }

}
