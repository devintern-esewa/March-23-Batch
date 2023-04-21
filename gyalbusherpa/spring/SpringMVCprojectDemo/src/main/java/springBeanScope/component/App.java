package springBeanScope.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        /*
        Singleton

        SingletonDemo obj1 = context.getBean("singletonDemo",SingletonDemo.class);
        SingletonDemo obj2 = context.getBean("singletonDemo",SingletonDemo.class);

        System.out.println(obj1 + " " + obj2);
         if (obj1 == obj2) {
            System.out.println("same object");
        } else {
            System.out.println("not same object");
        }

         */

        /*
        Prototype

        It will be instanced only when we call context.getBean();

        PrototypeDemo obj1 = context.getBean("prototypeDemo", PrototypeDemo.class);
        PrototypeDemo obj2 = context.getBean("prototypeDemo", PrototypeDemo.class);
        System.out.println(obj1 + " " + obj2);

        if (obj1 == obj2) {
            System.out.println("same object");
        } else {
            System.out.println("not same object");
        }

         */

        School school1 = context.getBean("school",School.class);
        School school2 = context.getBean("school",School.class);

        if (school1 == school2) {
            System.out.println("same object");
        } else {
            System.out.println("not same object");
        }

        System.out.println(school1.getStudent());
        System.out.println(school2.getStudent());

    }
}
