package week_2.day8.map;

import java.util.*;

public class F2_HashMap {

    /*
        - The underlying data structure is hashtable.
        - Insertion order is not preserved.
        - It is based on hashCode of keys.
        - Duplicate keys are not allowed but duplicate values are allowed.
        - Heterogeneous objects are allowed for both key and value.
        - Null is allowed for key (only once)
        - Null is allowed for values (any number of times)
        - HashMap implements Serializable and Cloneable Interface but not RandomAccess
        - It is best for search operation.
     */

    /*
            Constructor:

            1) HashMap m = new HashMap()
                    initial capacity = 16
                    fill ratio/load factor = 0.75

            2) HashMap m = new HashMap(int initialCapacity)

            3) HashMap m = new HashMap(int initialCapacity, float loadFactor)

            4) HashMap m = new HashMap(Map m)
     */

    public static void main(String[] args) {
        HashMap h = new HashMap();
        h.put("101", "don");
        h.put("102", "hero");
        h.put("103", "don");
        System.out.println(h);
        System.out.println(h.put("dons", 1000));

        Set s = h.keySet();
        System.out.println(s);

        Collection c = h.values();
        System.out.println(c);

        Set s1 = h.entrySet();
        System.out.println(s1);

        Iterator i = s1.iterator();
        while (i.hasNext()) {
            Map.Entry e = (Map.Entry) i.next();
            System.out.println(e.getKey() + " " + e.getValue());
        }

    }

}
