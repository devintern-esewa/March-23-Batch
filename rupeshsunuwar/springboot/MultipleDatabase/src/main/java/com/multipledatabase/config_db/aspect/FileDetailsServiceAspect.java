//package com.multipledatabase.config_db.aspect;
//
//
//import com.multipledatabase.inventory_db.entity.Product;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Aspect
//@Component
//public class FileDetailsServiceAspect {
//
//
//    Pro
//
//
//
//
//    @Before(value="execution(* com.multipledatabase.config_db.services.FileDetailsServiceImpl.checkProduct(..))) and args(product,  productList)")
//    public boolean beforeAdvice(JoinPoint joinPoint, Product product, List<Product> productList){
//
//        boolean flag = true;
//
//        for (Product product1 : productList) {
//
//            if (product.getCode().equals(product1.getCode()) || product.getStatus().equals(product1.getStatus())) {
//                flag = false;
//                break;
//            }
//        }
//        return flag;
//
//
//    }
//
//
//    }
//
