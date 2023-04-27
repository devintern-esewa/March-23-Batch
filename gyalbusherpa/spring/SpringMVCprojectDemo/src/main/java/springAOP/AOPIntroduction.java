package springAOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPIntroduction {

    // Sample

    /*
    @Before
    @After
    @Around
    @AfterReturning
    @AfterThrowing
     */
    public void beginTx(){
        // use one advice
    }

    @Pointcut("execution(public * com.app.ProductDao.*(..))")
    public void p1(){

    }

    @Before("p1()") // This is join point
    public void beginTxx(){

    }

    /*
        Aspect : It is a feature or functionality that cross-cuts over objects. It has a set of APIs that provides
                 cross-cutting requirements. ( A service )
                 It is a class, that provides additional services to project.
                 eg: transaction management, logging, security, encode and decode, etc.

        Advice : Advice provides concrete code implementation for the aspect.
                 It is a method inside aspect class. [It is actual implementation]

                 Types of advice (5):
                 - Before Advice : execute advice before calling b.method
                 - After advice : execute advice after b.method finish
                 - Around advice : has 2 two parts: executes before and after
                 - after returning advice : only on success
                 - after throwing advice : only if throws exception

        Pointcut : A pointcut tells us about the join points where aspects will be applied.
                   It is expression, it will select business method which need advices.
                   It never specifies which advices.

                   Syntax
                   Access specifier     return type     pack.class.Method(PARAM)

                   Allowed Symbols
                   * .

                   Example:

                   1) public int com.dao.EmployeeDao.*(..)

        JoinPoint : It defines the various execution points where an aspect can be applied.
                    It is a combination of advice + point cut
                    in simple connecting b.methods with required advices.


        Target : It is a business object that comprises aspect business core concern.
                 Pure business class object

        weaving: It represents a mechanism of associating cross-cutting concern to core concern dynamically
                 Process of mixing b.class methods and their connected advices

        proxy : Proxy is an object produced through weaving.
            (final output that contains both logic connected)
            - in static proxy, static method is used to develop and maintain proxy classes for each business method.
            - in dynamic proxy, proxy is developed at run time.

        weaver : code that provides proxies

        proxy design pattern : Actual object is wrapped into another object known as proxy and substitutes that
        object in place of actual object.

     */

    /*
    Implementation

    1) Spring AOP using XML based configuration (legacy style)
    2) Spring AOP using AspectJ (annotations)
     */

    /*
    Annotations:
    @Aspect
    @Before
    @After
    @Around
    @AfterReturning
    @AfterThrowing
    @Pointcut
     */

}
