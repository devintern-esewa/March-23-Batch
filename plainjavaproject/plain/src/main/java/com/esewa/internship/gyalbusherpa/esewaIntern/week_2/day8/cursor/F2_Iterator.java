package week_2.day8.cursor;

import java.util.ArrayList;
import java.util.Iterator;

public class F2_Iterator {

    // we can apply iterator concept for any collection object hence it is universal cursor
    // by using iterator we can perform both read and remove operations.

    /*
        METHODS:
        public boolean hasNext()
        public Object next()
        public void remove()

        Iterator i = c.iterator();

     */

    /*
        Limitations
        - only forward direction
        - replace and addition of new object is not possible
        (to overcome this we use ListIterator)
     */

    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        for (int i = 0; i < 10; i++) {
            al.add(i);
        }
        System.out.println(al);

        Iterator i = al.iterator();
        while (i.hasNext()) {
            Integer i2 = (Integer) i.next();
            if (i2 % 2 == 0) {
                System.out.println(i2);
            } else {
                i.remove();
            }
        }
        System.out.println(al);

    }
}
