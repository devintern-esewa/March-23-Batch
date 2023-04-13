package week_2.day8.sorting;

import java.util.TreeSet;

public class F1_Comparable {
    /*
        this interface is present in java.lang package it contains only one method CompareTo()
            public int CompareTo(Object obj)

        example:
        obj1.CompareTo(obj2)

        returns -ve iff obj1 has to come before obj2
        returns +ve iff obj1 has to come after obj2
        returns 0 iff obj1 & obj2 are equal

     */
    /*
        - If we are not satisfied with default natural sorting order or if the default natural sorting order is not
        already available then we can define our own customized sorting by using Comparator.
        - Comparable is meant for Default Natural Sorting order whereas Comparator is meant for customized Sorting
        order.
     */
    public static void main(String[] args) {
        System.out.println("A".compareTo("Z"));
        System.out.println("Z".compareTo("B"));
        System.out.println("A".compareTo("A"));
//        System.out.println("A".compareTo(null)); -> null pointer exception

        TreeSet t = new TreeSet();
        t.add("B");
        t.add("Z"); // "Z".compareTo("B"); +ve
        t.add("A"); // "A".compareTo("B"); -'ve
        System.out.println(t); // [A,B,Z]
        /*

            If we depend on natural sorting order internally JVM will call compareTo() method and will insert objects
             to the TreeSet.
            Hence, the objects should be comparable.
         */
    }
}
