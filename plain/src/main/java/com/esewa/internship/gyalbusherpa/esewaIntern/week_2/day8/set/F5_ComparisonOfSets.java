package week_2.day8.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class F5_ComparisonOfSets {
    /*
            Property                    HashSet             LinkedHashSet               TreeSet

            underlying data structure   hashtable           hashtable + linkedList      not applicable
            sorting order               not applicable      not applicable              applicable
            heterogeneous object        allowed             allowed                     not allowed
            duplicate objects           not allowed         not allowed                 not allowed
            null acceptance             allowed(only once)  allowed(only once)          not allowed

     */
    public static void main(String[] args) {
        HashSet h = new HashSet();
        h.add(null);
        h.add(1);
        h.add(null);
        System.out.println(h);

        LinkedHashSet h2 = new LinkedHashSet();
        h2.add(null);
        h2.add(1);
        h2.add(null);
        System.out.println(h2);

        TreeSet h3 = new TreeSet();
//        h3.add(null); -> throws null pointer exception

        System.out.println(h3);


    }
}
