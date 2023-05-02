//package com.example.mulltipledbconnectiontask.inventory.aop;
//
//import com.example.mulltipledbconnectiontask.inventory.model.Product;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.SerializationUtils;
//
//import javax.crypto.Cipher;
//import java.util.Base64;
//import java.util.List;
//
//@Aspect
//@Configuration
//public class AspectConfig {
//    @Autowired
//    private AesEncryptor aesEncryptor;
//
//    @Before("execution(* com.example.mulltipledbconnectiontask.inventory.model.Product.setCode(String)) && args(product)")
//    public void encryptProductCode(JoinPoint joinPoint,Product product){
//        String originalCode=product.getCode();
//       String encryptedData= aesEncryptor.convertToDatabaseColumn(originalCode);
//        System.out.println(encryptedData);
//      product.setCode(encryptedData);
//    }
//}
