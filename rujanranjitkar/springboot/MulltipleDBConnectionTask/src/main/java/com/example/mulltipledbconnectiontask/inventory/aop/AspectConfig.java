package com.example.mulltipledbconnectiontask.inventory.aop;

import com.example.mulltipledbconnectiontask.inventory.dto.ProductResponseDto;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class AspectConfig {
    @Autowired
    private AesEncryptor aesEncryptor;

    @Before("execution(* com.example.mulltipledbconnectiontask.inventory.service.ProductServiceImpl.countSuccessFailureBeforeSavingProducts(..)) && args(products,filePath)")
    public void encryptProductCode(List<Product> products, String filePath) {
        for (Product product : products) {
            String encryptedCode = aesEncryptor.convertToDatabaseColumn(product.getCode());
            product.setCode(encryptedCode);
        }
    }

    @Around("execution(* com.example.mulltipledbconnectiontask.inventory.service.ProductServiceImpl.getAllProducts(..))")
    public Object decryptProductsCode(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        List<ProductResponseDto> productResponseDtoList = (List<ProductResponseDto>) proceedingJoinPoint.proceed(); // executes the original method and returns its result
        for (ProductResponseDto productResponseDto : productResponseDtoList) {
            String decryptedCode = (String) aesEncryptor.convertToEntityAttribute(productResponseDto.getCode());
            productResponseDto.setCode(decryptedCode);
        }
        return productResponseDtoList;
    }

    @Around("execution(* com.example.mulltipledbconnectiontask.inventory.service.ProductServiceImpl.getProductById(..)) && args(productId)")
    public Object decryptProductCode(ProceedingJoinPoint proceedingJoinPoint, Long productId) throws Throwable {
        ProductResponseDto productResponseDto = (ProductResponseDto) proceedingJoinPoint.proceed();
        String decryptedCode = (String) aesEncryptor.convertToEntityAttribute(productResponseDto.getCode());
        productResponseDto.setCode(decryptedCode);
        return productResponseDto;
    }
}
