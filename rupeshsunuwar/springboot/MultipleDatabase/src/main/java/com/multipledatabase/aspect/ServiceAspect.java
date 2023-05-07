package com.multipledatabase.aspect;


import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.multipledatabase.security.Cipher.decryption;
import static com.multipledatabase.security.Cipher.encryption;

@Aspect
@Configuration
public class ServiceAspect {
    @Before(value = "execution(* com.multipledatabase.inventorydb.services.ProductServiceImpl.addAllProduct(..))")
    public void beforeAdvice(JoinPoint joinPoint) {

        List<Product> joinPointArg = (List<Product>) joinPoint.getArgs()[0];
        for (Product products : joinPointArg) {
            products.setCode(encryption(products.getCode()));
        }
        System.out.println(joinPointArg);
    }

    @AfterReturning(value = "execution(* com.multipledatabase.inventorydb.services.ProductServiceImpl.getAllProduct(..))", returning = "product")
    public void afterReturningAdvice(List<ProductDto> product) {

        for (ProductDto productDto : product) {

            productDto.setCode(decryption(productDto.getCode()));
        }

    }


    @AfterReturning(value = "execution(* com.multipledatabase.inventorydb.services.ProductServiceImpl.findByName(..))", returning = "product")
    public void afterReturning(ProductDto product) {

        product.setCode(decryption(product.getCode()));

    }
}




