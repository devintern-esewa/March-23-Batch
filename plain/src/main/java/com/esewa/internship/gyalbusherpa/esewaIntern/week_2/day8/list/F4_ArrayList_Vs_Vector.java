package week_2.day8.list;

import java.util.*;

public class F4_ArrayList_Vs_Vector {

    /*

        ArrayList               Vector

        non-synchronized        - synchronized
        not thread-safe         - thread-safe
        performance is high     - performance is low
        1.2v                    - 1.0v (legacy)

     */
    public static void main(String[] args) {

        // How to get synchronized version of ArrayList Object?

        /*
            By default, ArrayList is Object is non-synchronized, but we can get synchronized version of ArrayList by
            using Collection class synchronizedList() method.

         */
        ArrayList<String> arr = new ArrayList<>();
        arr.add("don");
        List<String> l = Collections.synchronizedList(arr);

        // public static List synchronizedList(List l)

        // Similarly we can get Synchronized version of Set, Map Objects by using the following methods of Collection
        // class.
            // public static Set synchronizedSet(Set s)
            // public static Map synchronizedSet(Map m)


    }
}
