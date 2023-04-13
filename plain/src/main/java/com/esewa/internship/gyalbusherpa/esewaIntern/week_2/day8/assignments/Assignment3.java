package week_2.day8.assignments;

import java.util.TreeMap;

public class Assignment3 {
    public static void main(String[] args) {

        // using comparable

        TreeMap<Student, Integer> stud = new TreeMap<>();
        Student st1 = new Student("Don", 100);
        Student st2 = new Student("Hero", 50);
        Student st3 = new Student("Zero", 0);
        Student st4 = new Student("Ayyer", 69);
        Student st5 = new Student("Benu gopal Ayyer", 59);

        int m1 = st1.getMarks();
        int m2 = st2.getMarks();
        int m3 = st3.getMarks();
        int m4 = st4.getMarks();
        int m5 = st5.getMarks();

        stud.put(st1, m1);
        stud.put(st2, m2);
        stud.put(st3, m3);
        stud.put(st4, m4);
        stud.put(st5, m5);

        System.out.println(stud);

        // using comparator

        TreeMap stud2 = new TreeMap<>(new StudentDescending());
        stud2.put(st1, m1);
        stud2.put(st2, m2);
        stud2.put(st3, m3);
        stud2.put(st4, m4);
        stud2.put(st5, m5);

        System.out.println(stud2);


    }
}
