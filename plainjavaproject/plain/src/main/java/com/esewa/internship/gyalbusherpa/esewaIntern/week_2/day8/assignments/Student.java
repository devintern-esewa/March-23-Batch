package week_2.day8.assignments;

import java.util.Comparator;

public class Student implements Comparable {
    String name;
    int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public String toString() {
        return name + " " + marks;
    }

    @Override
    public int compareTo(Object o) {
        String s1 = this.name;
        Student s = (Student) o;
        String s2 = s.name;
        return s1.compareTo(s2);
    }
}

class StudentDescending implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Student s = (Student) o1;
        Student s1 = (Student) o2;

        String n1 = s.name;
        String n2 = s1.name;

        return n2.compareTo(n1);
    }
}

