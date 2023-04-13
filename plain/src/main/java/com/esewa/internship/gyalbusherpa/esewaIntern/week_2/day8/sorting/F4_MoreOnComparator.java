package week_2.day8.sorting;

import java.util.Comparator;
import java.util.TreeSet;

public class F4_MoreOnComparator {

    /*

        1) If we depend on natural sorting order then objects should be homogenous and comparable otherwise we will
        get runtime exception
        2) But if we are defining our own sorting by comparator then objects don't have to be homogenous and
        comparable. We can insert heterogeneous non-scomparable objects also.

     */

    /*
    Write a program to insert String and StringBuffer objects into the TreeSet where sorting order is increasing
    length order if two objects having the same length then consider their alphabetic order.
     */
    public static void main(String[] args) {

        // sorting according to length order

        TreeSet t = new TreeSet(new custom());
        t.add("A");
        t.add(new StringBuffer("ABC"));
        t.add(new StringBuffer("DAbC"));
        t.add(new StringBuffer("AA"));
        t.add("XX");
        t.add("A");
        System.out.println(t);
    }
}

class custom implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String s = o1.toString();
        String s2 = o2.toString();

        int l = s.length();
        int l2 = s2.length();

        if (l < l2) {
            return -1;
        } else if (l > l2) {
            return +1;
        } else {
            return s.compareTo(s2);
        }
    }
}
