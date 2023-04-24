package springAOP.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* springAOP.example.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        System.out.println(jp.getSignature());
//        System.out.println(jp.getArgs()[0].toString());
        System.out.println("loggers");
    }

    @After("execution(* springAOP.example.ShoppingCart.checkout(..))")
    public void afterLogger(){
        System.out.println("After loggers");
    }

    @Pointcut("execution(* springAOP.example.ShoppingCart.quantity(..))")
    public void afterReturningPointCut(){

    }

    @AfterReturning(pointcut = "afterReturningPointCut()",returning = "retVal")
    public void afterReturning(String retVal){
        System.out.println("After returning" + retVal);
    }

}
