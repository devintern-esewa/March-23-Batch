package week_2.day8.set;

import java.util.TreeSet;

public class F4_TreeSet {
    /*
        - Balanced Tree
        - duplicate not allowed
        - insertion order not applicable
        - inserted based on certain sorting algo
        - heterogeneous objects are not allowed -> gets runtime exception
        - null is accepted (only once)
     */

    /*
        Constructors

        TreeSet t = new TreeSet(); -> default natural sorting
        TreeSet t = new TreeSet(Comparator c); -> customized sorting
        TreeSet t = new TreeSet(Collection c);
        TreeSet t = new TreeSet(SortedSet s);
     */

    public static void main(String[] args) {
        TreeSet t = new TreeSet();
        t.add("a");
        t.add("A");
        t.add("z");
        t.add("b");

//        t.add(new StringBuffer("don")); -> not possible because StringBuffer doesn't implement Comparable
        System.out.println(t);
    }
}
