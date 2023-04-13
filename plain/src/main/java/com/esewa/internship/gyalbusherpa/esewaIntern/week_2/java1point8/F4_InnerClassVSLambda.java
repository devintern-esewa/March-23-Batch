package week_2.java1point8;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

class Employee {
    String name;
    int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}

public class F4_InnerClassVSLambda {
    /*
        Anonymous Inner class != Lambda Expressions

        If anonymous Inner class implements an interface that contains single abstract method then only we can
        replace that anonymous inner class with lambda expression.

        Anonymous Inner class can extend a normal class
        Anonymous Inner class can extend an abstract class
        Anonymous Inner class can implement an interface which contains any number of abstract methods

        Lambda expression can implement an interface which contains a single abstract method(FI)

        Anonymous inner class > lambda expression

     */
    public static void main(String[] args) {
        ArrayList<Employee> emp = new ArrayList<>();
        emp.add(new Employee("don", 1));
        emp.add(new Employee("don", 2));
        emp.add(new Employee("hero", 1));
        emp.add(new Employee("son", 3));
        emp.add(new Employee("hero", 4));
        Collections.sort(emp, (o1, o2) ->
                (o1.id < o2.id) ? -1 : (o1.id > o2.id) ? 1 : (o1.name).compareTo(o2.name));
//        System.out.println(emp);


        // Anonymous Inner Class

        Thread t = new Thread() {
            // some code.
            // we are writing a class that extends Thread class
        };

        Runnable r = new Runnable() {
            // impl class for Runnable;
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("child");
                }
            }
        };
        Thread t2 = new Thread(r);
        t2.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }
    }
}
