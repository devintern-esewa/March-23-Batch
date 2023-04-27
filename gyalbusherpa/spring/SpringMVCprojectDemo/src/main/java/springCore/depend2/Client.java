package springCore.depend2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
//        Student stu = context.getBean("stu",Student.class);
//        stu.cheating();
//
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
//        AnotherStudent stu = context.getBean("stu2",AnotherStudent.class);
//        stu.startCheating();

        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Student stu = context.getBean("stu",Student.class);
        stu.cheating();
    }
}
