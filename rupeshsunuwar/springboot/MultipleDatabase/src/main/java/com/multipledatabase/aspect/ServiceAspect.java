//package com.multipledatabase.config_db.aspect;
//
//
//import com.multipledatabase.inventorydb.entity.Product;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//import static com.multipledatabase.security.Cipher.encryption;
//
//@Aspect
//@Configuration
//public class ServiceAspect {
//    @Before(value = "execution(* com.multipledatabase.inventorydb.services.ProductServiceImpl(..))) and args(productList)")
//    public void beforeAdvice(List<Product> productList) {
//
//        for (Product product1 : productList) {
//            encryption(product1.getCode(), "Raja");
//        }
//    }
//
//
//}
//
