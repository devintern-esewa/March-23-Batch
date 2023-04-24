package week_2.day8.map;

import java.util.Comparator;
import java.util.TreeMap;

public class F6_TreeMap {
    /*
        - Red Black tree - underlying ds
        - duplicate not allowed (value are allowed)
        - insertion order is not preserved
        - inserted based on certain sorting order of key
        - If we depend on default natural sorting order then keys should be homogeneous and comparable
          otherwise we will get runtime exception saying classCasteException. If we are defining our own sorting by
          comparator then keys need not be homogeneous and comparable. We can take heterogeneous non-comparable object
          also.
        - Whether we depend on natural sorting order or customized sorting order, there are no restriction for
          values. We can take heterogeneous non-comparable objects also.
        - null is not accepted for key. for value, it is accepted.
     */

    /*
        Constructors

        TreeMap t = new TreeMap(); -> default natural sorting
        TreeMap t = new TreeMap(Comparator c); -> customized sorting
        TreeMap t = new TreeMap(SortedSet s);
        TreeMap t = new TreeMap(Map m);
     */
    public static void main(String[] args) {

        // natural sorting

//        TreeMap m = new TreeMap();
//        m.put(100,"ZZZ");
//        m.put(103,"YYY");
//        m.put(101,"XXX");
//        m.put(104,106);
////        m.put("don",53); -> class cast exception
////        m.put(null,'d'); -> null pointer exception
//        System.out.println(m);

        // customized sorting
        TreeMap t = new TreeMap(new myComp());
        t.put("XXX",10);
        t.put("AAA",20);
        t.put("ZZZ",30);
        t.put("LLL",40);
        System.out.println(t);

    }
}

class myComp implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String s = (String)o1;
        String s2 = (String)o2;

        return s2.compareTo(s);
    }
}
