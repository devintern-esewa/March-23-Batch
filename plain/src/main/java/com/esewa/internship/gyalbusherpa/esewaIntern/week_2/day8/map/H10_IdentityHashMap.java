package week_2.day8.map;

import java.util.HashMap;
import java.util.IdentityHashMap;

public class H10_IdentityHashMap {

    /*
        - It is exactly same as hashMap (including methods and constructor).
            expect the following difference.

         - In the case of normal hashMap jvm will use .equals() method to identify duplicate keys, which is meant for
          content comparison
         but in the case of identityHashMap, jvm will use == operator to identify duplicate keys which is meant for
         reference comparison(address)

     */
    public static void main(String[] args) {

        // hashMap use .equals method so here value is .equals()
        // hence, the kyes are considered duplicate.

        HashMap m = new HashMap();
        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);

        m.put(i1, "don");
        m.put(i2, "son");
        System.out.println(m);

        // IdentityHashMap uses == to check for duplicate.

        IdentityHashMap m2 = new IdentityHashMap();
        Integer i3 = new Integer(10);
        Integer i4 = new Integer(10);

        m2.put(1, "dods");
        m2.put(1, "dofdds");

        m2.put(i3, "don");
        m2.put(i4, "son");

        System.out.println(m2);

    }

}
