package week_2.java1point8;

import java.util.ArrayList;
import java.util.function.Predicate;

class Emp {
    String name;
    double salary;

    public Emp(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

public class F6_Predefined_Predicate {
    /*
        Predefined Functional Interfaces:
            - Predicate
            - Function
            - Consumer
            - Supplier

        Two argument predefined functional interfaces:
            - BiPredicate
            - BiFunction
            - BiConsumer

        Primitive Functional interfaces
            - IntPredicate
            - IntFunction
            - IntConsumer
     */

    /*

        Predicate:

        public abstract boolean test(T t)

        interface Predicate<T>{
            public boolean test(T t);
        }

     */

    public static void main(String[] args) {
        Predicate<Integer> p1 = i -> i % 2 == 0;
//        System.out.println(p1.test(10));
//        System.out.println(p1.test(15));
//        System.out.println(p1.test(102));
//        System.out.println(p1.test(1));

        Predicate<Employee> p2 = e -> e.name == "don" && e.id > 10;
//        System.out.println(p2);

        String k = "donisme";

        Predicate<String> p3 = p -> p.length() > 5;
//        System.out.println(p3.test(k));


        //WAP to check whether length of String is even or not
        String[] arr = {"don", "son", "chinnaa", "chinna swami", "venu gopal", "ayyer"};
        Predicate<String> p5 = k2 -> k2.length() % 2 == 0;
        for (String s1 :
                arr) {
            if (p5.test(s1)) {
//                System.out.println(s1);
            }
        }

        // WAP to check salary of emp more than 3000

        ArrayList<Emp> l = new ArrayList<>();
        l.add(new Emp("don", 1532));
        l.add(new Emp("son", 3553));
        l.add(new Emp("ron", 13532));
        l.add(new Emp("bon", 43234));
        l.add(new Emp("aon", 1352));
        l.add(new Emp("qon", 1232533));

        Predicate<Emp> pp = t -> t.salary > 3000;
        for (Emp e : l) {
            if (pp.test(e)) {
//                System.out.println(e.name + " " + e.salary);
            }

        }

        // Predicate joining
        int[] x= {2,35,3,5,24,43,4,34,354,31,34};
        Predicate<Integer> e1 = t-> t%2==0;
        Predicate<Integer> e2 = t-> t>10;

        for (int value: x) {
            if(e1.negate().test(value)){
                System.out.println("odd " + value);
            }
        }

        for (int i = 0; i < x.length; i++) {
            if(e1.and(e2).test(x[i])){
                System.out.println("even and greater than 10 are " + x[i]);
            }
        }



    }
}
