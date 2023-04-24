package week_2.day8.map;

import java.util.*;

public class H9_LinkedHashMap {
    /*
          - It is the child class of hashMap
          - It is exactly same as hashMap (including method and constructors) except the following differences.

        HashMap                                                     LinkedHashMap

        - underlying data structure hashtable                         LL + hashtable (hybrid ds)
        - insertion order is not preserved                            is preserved
          and is based on hashcode of keys
        - introduced in 1.2v                                          in 1.4v

     */

    /*
    LinkedHashSet and LinkedHashMap are commonly used for developing cache based applications.
     */
    public static void main(String[] args) {

        // all code is copied from hashMap.
        // after making it LinkedHashMap insertion order is preserved.

        LinkedHashMap h = new LinkedHashMap();
        h.put("101", "don");
        h.put("102", "hero");
        h.put("103", "don");
        System.out.println(h);
        System.out.println(h.put("dons", 1000));

//        Set s = h.keySet();
//        System.out.println(s);
//
//        Collection c = h.values();
//        System.out.println(c);
//
//        Set s1 = h.entrySet();
//        System.out.println(s1);

//        Iterator i = s1.iterator();
//        while (i.hasNext()) {
//            Map.Entry e = (Map.Entry) i.next();
//            System.out.println(e.getKey() + " " + e.getValue());
//        }
    }
}
