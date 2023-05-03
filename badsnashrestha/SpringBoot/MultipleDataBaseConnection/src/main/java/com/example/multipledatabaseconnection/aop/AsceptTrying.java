package com.example.multipledatabaseconnection.aop;

import com.example.multipledatabaseconnection.exception.IdNotFoundException;
import com.example.multipledatabaseconnection.productDetails.dto.ProductDetailsResponseDto;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.repo.ProductDetailsRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration

public class AsceptTrying {
    @Autowired
    private AesEncrypter aesEncrypter;
    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @Before("execution(* com.example.multipledatabaseconnection.productDetails.service.ProductDetailsServiceImpl.processingProduct(..)) && args(productDetails,filepath)")
    public void encryptProductCode(List<ProductDetails> productDetails, String filepath) {
        for (ProductDetails productDetail : productDetails) {
            String encryptedCode = aesEncrypter.convertToDatabaseColumn(productDetail.getCode());
            productDetail.setCode(encryptedCode);
        }
    }

    @Around("execution(* com.example.multipledatabaseconnection.productDetails.service.ProductDetailsServiceImpl.getAllProductDetails(..))")
    public Object decryptProductsCode(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        List<ProductDetailsResponseDto> productDetailsResponseDtoList = (List<ProductDetailsResponseDto>) proceedingJoinPoint.proceed();
        for (ProductDetailsResponseDto productDetailsResponseDto : productDetailsResponseDtoList) {
            String decryptedCode = (String) aesEncrypter.convertToEntityAttribute(productDetailsResponseDto.getCode());
            productDetailsResponseDto.setCode(decryptedCode);

        }
        return productDetailsResponseDtoList;
    }

    @Before("execution(* com.example.multipledatabaseconnection.productDetails.service.ProductDetailsServiceImpl.getProductDetailsById(..)) && args(productDetailsId)")
    public Object decryptProductCode(Long productDetailsId) {
        System.out.println(productDetailsId);
        ProductDetails productDetailsResponseDto = productDetailsRepo.findById(productDetailsId).orElseThrow(() -> new IdNotFoundException("Product with " + productDetailsId + " doesn't exists"));
        String decryptedCode = (String) aesEncrypter.convertToEntityAttribute(productDetailsResponseDto.getCode());
        productDetailsResponseDto.setCode(decryptedCode);
        return productDetailsResponseDto;

    }

}

