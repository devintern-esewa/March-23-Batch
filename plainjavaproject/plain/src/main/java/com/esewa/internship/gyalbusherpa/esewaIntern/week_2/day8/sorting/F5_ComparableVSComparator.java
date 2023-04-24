package week_2.day8.sorting;

import java.util.Comparator;
import java.util.TreeSet;

public class F5_ComparableVSComparator {

    /*

        1) For predefined Comparable classes like String default natural sorting order is already available. If we
           are not satisfied with that, we can define our own sorting by Comparator object.

        2) For predefined non-comparable classes like StringBuffer, default natural sorting order is not available.
           We can define required sorting by implementing comparator interface.

        3) For our own classes like Employee, Student, Customer, etc. the person who is writing our own class, he/she
           is responsible to define natural sorting order by implementing comparable interface. The person who is using
           our class, if he/she is not satisfied with default natural sorting order, then he/she can define their own
           sorting order by using comparator.

     */

    /*

        Comparable                              Comparator

        - for default natural sorting order       for Customized sorting order
        - present in java.lang                    present in java.util
        - only one method i.e. compareTo()        two methods 1) compare() 2) equals
        - all wrapper classes and String          only two classes implement comparator i.e. Collator and
        class implements comparable                     RuleBasedCollator

     */
    public static void main(String[] args) {

        Employee e1 = new Employee("don", 100);
        Employee e2 = new Employee("hero", 50);
        Employee e3 = new Employee("gopal", 200);
        Employee e4 = new Employee("chinna swami", 10);
        Employee e5 = new Employee("venu gopal ayyer", 1);
        Employee e6 = new Employee("nana", 100);

        TreeSet t = new TreeSet(); // for default natural sorting
        t.add(e1);
        t.add(e2);
        t.add(e3);
        t.add(e4);
        t.add(e5);
        t.add(e6);
        System.out.println(t);

        TreeSet t2 = new TreeSet(new CustomizedEmp()); // for customized sorting
        t2.add(e1);
        t2.add(e2);
        t2.add(e3);
        t2.add(e4);
        t2.add(e5);
        t2.add(e6);
        System.out.println(t2);
    }
}

class Employee implements Comparable {

    String name;
    int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public int compareTo(Object o) {
        int eId1 = this.id;
        Employee e = (Employee) o;
        int eId2 = e.id;

        if (eId1 < eId2) {
            return -1;
        } else if (eId1 > eId2) {
            return +1;
        } else {
            return 0;
        }
    }
}

class CustomizedEmp implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Employee e1 = (Employee) o1;
        Employee e2 = (Employee) o2;

        String s1 = e1.name;
        String s2 = e2.name;

        return s1.compareTo(s2);
    }
}
