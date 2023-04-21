package springCore.oic;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mobile {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MobileConfig.class);
        System.out.println("config loaded");

        Sim sim = context.getBean("airtel",Airtel.class);
        sim.data();
        sim.calling();
    }

}