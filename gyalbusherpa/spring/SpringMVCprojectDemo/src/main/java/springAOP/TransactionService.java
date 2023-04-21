package springAOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionService {

    @Pointcut("execution(public * springAOP.*(..))")
    public void p1(){}

    @Before("p1()")
    public void beginTx(){
        System.out.println("Tx started");
    }
    @After("p1()")
    public void commitTx(){
        System.out.println("Tx commited");
    }
}
