package week_2.day8.sorting;

import java.util.Comparator;
import java.util.TreeSet;

public class F3_ComparatorForString {
    public static void main(String[] args) {
        /*
        For default natural sorting

        TreeSet t = new TreeSet();
        t.add("don");
        t.add("son");
        t.add("gon");
        t.add("zon");
        t.add("ton");
        t.add("fon");
        System.out.println(t);
         */

        // for customized sorting
        TreeSet t = new TreeSet(new myComparatorString());
        t.add("don");
        t.add("son");
        t.add("gon");
        t.add("zon");
        t.add("ton");
        t.add("fon");
        System.out.println(t);

        // customized sorting for string buffer
        // if we are defining our own sorting by comparator, the objects don't have to be comparable.
        TreeSet t2 = new TreeSet(new myComparatorStringBuffer());
        t2.add(new StringBuffer("don"));
        t2.add(new StringBuffer("son"));
        t2.add(new StringBuffer("pon"));
        t2.add(new StringBuffer("fon"));
        t2.add(new StringBuffer("2on"));
        t2.add(new StringBuffer("1on"));

        System.out.println(t2);

    }
}

class myComparatorString implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String s = (String) (o1);
        String s2 = (String) (o2);

        return s2.compareTo(s);
    }
}

class myComparatorStringBuffer implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String s = o1.toString();
        String s2 = o2.toString();

        return s2.compareTo(s);
    }
}
