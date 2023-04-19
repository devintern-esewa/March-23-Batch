package week_2.day8.cursor;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class F5_ImplementationOfCursor {
    public static void main(String[] args) {
        Vector v = new Vector<>();
        Enumeration e = v.elements();
        Iterator i = v.iterator();
        ListIterator li = v.listIterator();

        System.out.println(e.getClass().getName());
        System.out.println(i.getClass().getName());
        System.out.println(li.getClass().getName());
    }

}
