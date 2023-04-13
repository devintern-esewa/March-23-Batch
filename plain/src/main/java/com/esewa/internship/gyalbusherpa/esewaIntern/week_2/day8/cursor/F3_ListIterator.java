package week_2.day8.cursor;

import java.util.LinkedList;
import java.util.ListIterator;

public class F3_ListIterator {

    /*
        - child of iterator

        - Bidirectional
        - read
        - remove
        - replacement
        - addition of new object

     */

    /*
        public ListIterator ListIterator() -> in list interface
        eg: ListIterator ltr = l.listIterator(); -> l is any collection

     */

    /*
        methods:

        // for forward movement
            public boolean hasNext()
            public Object next()
            public int nextIndex()

        // for previous movement
            public boolean hasPrevious()
            public Object previous()
            public int previousIndex()

        // extra
            public void remove()
            public void set(Object new)
            public void add(Object new)
     */

    /*
    ListIterator is the most powerful cursor but its limitation is, it is applicable only for List implemented class
    objects, and it is not a universal cursor.
     */


    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.add("ayyer");
        l.add("benu gopal ayyer");
        l.add("chinna swami mutthu swami benu gopal ayyer");
        System.out.println(l);

        ListIterator ltr = l.listIterator();
        while (ltr.hasNext()) {
            String s = (String) ltr.next();
            if (s.equals("ayyer")) {
                ltr.remove();
            } else if (s.equals("benu gopal ayyer")) {
                ltr.add("trigi palli yagga parambil param badur chinna swami muthhu swami benu gopal ayyer");
            } else if (s.equals("chinna swami mutthu swami benu gopal ayyer")) {
                ltr.set("raja shekhara jaisurya atuu pattu trigi palli yagga parambil param badur chinna swami muthhu" +
                        " swami benu gopal ayyer");
            }
        }
        System.out.println(l);


    }

}
