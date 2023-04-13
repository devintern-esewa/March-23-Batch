package week_2.java1point8;

import java.util.function.Function;
import java.util.function.Predicate;

class Student2 {
    String name;
    int mark;

    public Student2(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }
}

public class F7_Predefined_Function {
    /*
        interface Function<T,R>
        {
            public R apply(T t);
        }

     */
    public static void main(String[] args) {
        Function<String, Integer> f = i -> i.length();
//        System.out.println(f.apply("don"));

        Function<Integer, Integer> f2 = i -> i * i;
//        System.out.println(f2.apply(3));

        Function<String, String> f3 = i -> i.toUpperCase();
//        System.out.println(f3.apply("hero"));

        Function<Student2, String> f4 = i -> {
            int marks = i.mark;
            String grade = (marks >= 80) ? "A" : (marks >= 60) ? "B" : (marks >= 50) ? "C" : (marks >= 35) ? "D" : "E";
            return grade;
        };
        Predicate<Student2> p = i-> i.mark>60;

        Student2[] s = {
                new Student2("Hi", 342),
                new Student2("bads", 42),
                new Student2("ag", 72),
                new Student2("tea", 2),
                new Student2("nafd", 20),
        };

        for (Student2 aa : s) {
            System.out.println("student name: " + aa.name);
            System.out.println("student marks: " + aa.mark);
            System.out.println("student grade: " + f4.apply(aa));
            System.out.println();
            if(p.test(aa)){
                System.out.println(aa.name);
            }
            System.out.println();
        }

        // Function chaining.

        System.out.println(f.andThen(f2).apply("don"));

        // first f1 followed by f2

        System.out.println(f2.compose(f).apply("d0"));



    }
}
